package fre.shown.tryboot.service;

import fre.shown.tryboot.config.MqConfig;
import fre.shown.tryboot.dao.GoodDAO;
import fre.shown.tryboot.dao.SeckillOrderDAO;
import fre.shown.tryboot.domain.ResultVO;
import fre.shown.tryboot.domain.order.SeckillGoodInfoDTO;
import fre.shown.tryboot.domain.order.SeckillOrderDO;
import fre.shown.tryboot.domain.order.SeckillOrderDTO;
import fre.shown.tryboot.domain.order.SeckillOrderDetailVO;
import fre.shown.tryboot.service.redis.RedisService;
import fre.shown.tryboot.util.CaptchaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Radon Freedom
 * created at 2019.04.27 下午5:17
 */

@Service
public class OrderServiceImpl implements OrderService {

    private static final String VERIFY_CODE_PREFIX = "verify_code";
    private static final String SECKILL_PATH_PREFIX = "seckill_path";
    private static final String NO_STOCK_SECKILL_GOOD_KEY_PREFIX = "no_stock";
    private static final String SECKILL_RESULT_KEY_PREFIX = "seckill_result";

    private static final long TIMEOUT_30_S = 30;
    private static final long TIMEOUT_300_MS = 300;
    private static final TimeUnit SEC = TimeUnit.SECONDS;
    private static final TimeUnit MILLIS = TimeUnit.MILLISECONDS;

    private static final String DUMMY = "";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SeckillOrderDAO seckillOrderDAO;
    private final GoodDAO goodDAO;
    private final RedisService redisService;
    private final AmqpTemplate amqpTemplate;
    private final PasswordEncoder pathEncoder = new BCryptPasswordEncoder();

    public OrderServiceImpl(SeckillOrderDAO seckillOrderDAO, GoodDAO goodDAO, RedisService redisService, AmqpTemplate amqpTemplate) {
        this.seckillOrderDAO = seckillOrderDAO;
        this.goodDAO = goodDAO;
        this.redisService = redisService;
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public void getSeckillVerifyCode(Long seckillGoodId, HttpServletResponse response) {

        String verifyCode;
        try {
            verifyCode = CaptchaUtils.createVerifyCode(response);
        } catch (ScriptException | IOException e) {
            e.printStackTrace();
            return;
        }

        String key = VERIFY_CODE_PREFIX + seckillGoodId + verifyCode;
        redisService.set(key, DUMMY, TIMEOUT_30_S, SEC);
    }

    @Override
    public ResultVO<String> getSeckillPath(String username, Long seckillGoodId, String verifyCode) {

        ResultVO<String> result = new ResultVO<>();

        String verifyCodeKey = VERIFY_CODE_PREFIX + seckillGoodId + verifyCode;
        if (!redisService.hasKey(verifyCodeKey)) {
            result.setErrorMsg("验证码有误");
            return result;
        }

        redisService.delete(verifyCodeKey);
        String pathKey = SECKILL_PATH_PREFIX + username + seckillGoodId;

        String path = pathEncoder.encode(username + seckillGoodId + verifyCode).replaceAll("/", "");
        redisService.set(pathKey, path, TIMEOUT_300_MS, MILLIS);
        result.setSuccecssData(path);
        return result;
    }

    /**
     * 1. 秒杀请求先访问 Redis 判断是否有库存, 如果是, 下一步; 否则返回失败. <br/>
     * 2. 秒杀请求进入消息队列, 通知客户端轮询秒杀结果.
     *
     * @param seckillOrderDTO 前端传入参数
     * @param path            秒杀路径
     * @return 通知前端轮询秒杀结果，或者秒杀失败
     */
    @Override
    public ResultVO<Object> trySeckill(SeckillOrderDTO seckillOrderDTO, String path) {

        ResultVO<Object> result = new ResultVO<>();

        String pathKey = SECKILL_PATH_PREFIX + seckillOrderDTO.getUsername() + seckillOrderDTO.getSeckillGoodId();
        String realPath = redisService.get(pathKey, String.class);
        logger.debug("request path: " + path);
        logger.debug("real path: " + realPath);
        if (realPath == null || !realPath.equals(path)) {
            result.setErrorMsg("请求有误！");
            return result;
        }

        redisService.delete(pathKey);
        if (!redisService.hasKey(NO_STOCK_SECKILL_GOOD_KEY_PREFIX + seckillOrderDTO.getSeckillGoodId())) {
            amqpTemplate.convertAndSend(MqConfig.SECKILL_ORDER_QUEUE, seckillOrderDTO);
            result.setSuccess(true);
        } else {
            result.setErrorMsg("库存不足, 秒杀失败!");
        }

        return result;
    }

    /**
     * 1. 快速判断商品是否没有库存，若否，在Redis保存秒杀结果并返回 <br/>
     * 2. 判断同一用户同一商品的订单是否存在，若是，在Redis保存秒杀结果并返回 <br/>
     * 3. 尝试减库存，如果失败，在Redis中保存结果并返回 <br/>
     * 4. 尝试生成订单信息，如果失败，保存结果并抛出异常（为了让减库存操作回滚）
     *
     * @param seckillOrderDTO 从消息队列 {@link MqConfig#SECKILL_ORDER_QUEUE SECKILL_ORDER_QUEUE} 中取出下一个要生成的订单
     */
    @RabbitListener(queues = MqConfig.SECKILL_ORDER_QUEUE)
    @Transactional(rollbackFor = Throwable.class)
    protected void createSeckillOrder(SeckillOrderDTO seckillOrderDTO) {

        ResultVO<Long> result = new ResultVO<>();

        if (redisService.hasKey(NO_STOCK_SECKILL_GOOD_KEY_PREFIX + seckillOrderDTO.getSeckillGoodId())) {
            result.setErrorMsg("库存不足, 秒杀失败!");
            setSeckillResult(seckillOrderDTO.getUsername(), seckillOrderDTO.getSeckillGoodId(), result);
            return;
        }

        Long id = seckillOrderDAO.isOrderExisted(seckillOrderDTO.getUsername(), seckillOrderDTO.getSeckillGoodId());
        if (id != null) {
            result.setSuccessDataAndMsg(id, "您已经参加过该商品的秒杀!");
            setSeckillResult(seckillOrderDTO.getUsername(), seckillOrderDTO.getSeckillGoodId(), result);
            return;
        }

        if (!goodDAO.reduceSeckillGoodStock(seckillOrderDTO.getSeckillGoodId(), seckillOrderDTO.getGoodCnt())) {
            result.setErrorMsg("库存不足, 秒杀失败!");
            setSeckillResult(seckillOrderDTO.getUsername(), seckillOrderDTO.getSeckillGoodId(), result);
            if (goodDAO.hasStock(seckillOrderDTO.getSeckillGoodId()) == null) {
                redisService.set(NO_STOCK_SECKILL_GOOD_KEY_PREFIX + seckillOrderDTO.getSeckillGoodId(), DUMMY);
            }
            return;
        }

        SeckillGoodInfoDTO seckillGoodInfoDTO = goodDAO.getSeckillGoodInfoById(seckillOrderDTO.getSeckillGoodId());

        SeckillOrderDO seckillOrderDO = new SeckillOrderDO();
        seckillOrderDO.setUsername(seckillOrderDTO.getUsername());
        seckillOrderDO.setSeckillGoodId(seckillOrderDTO.getSeckillGoodId());
        seckillOrderDO.setDeliveryInfoId(seckillOrderDTO.getDeliveryInfoId());
        seckillOrderDO.setOrderChannel(seckillOrderDTO.getOrderChannel());
        seckillOrderDO.setGoodCount(seckillOrderDTO.getGoodCnt());

        seckillOrderDO.setGoodId(seckillGoodInfoDTO.getGoodId());
        seckillOrderDO.setGoodName(seckillGoodInfoDTO.getGoodName());
        seckillOrderDO.setSeckillPrice(seckillGoodInfoDTO.getSeckillPrice());
        seckillOrderDO.setGoodPrice(seckillGoodInfoDTO.getGoodPrice());
        seckillOrderDO.setStatus(0);


        try {
            seckillOrderDAO.addOrder(seckillOrderDO);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            result.setErrorMsg("秒杀失败, 您已经参与了秒杀!");
            setSeckillResult(seckillOrderDTO.getUsername(), seckillOrderDTO.getSeckillGoodId(), result);
            throw e;
        }

        result.setSuccecssData(seckillOrderDO.getId());
        setSeckillResult(seckillOrderDTO.getUsername(), seckillOrderDTO.getSeckillGoodId(), result);
    }

    private void setSeckillResult(String username, Long seckillGoodId, ResultVO<Long> result) {
        String key = SECKILL_RESULT_KEY_PREFIX + username + seckillGoodId;
        redisService.set(key, result);
    }


    @Override
    @SuppressWarnings("unchecked")
    public ResultVO<Long> getSeckillResult(String username, Long seckillGoodId) {

        String key = SECKILL_RESULT_KEY_PREFIX + username + seckillGoodId;
        ResultVO<Long> resultVO;

        if (redisService.hasKey(key)) {
            resultVO = redisService.get(key, ResultVO.class);
            redisService.delete(key);
        } else {
            resultVO = new ResultVO<>();
            resultVO.setSuccess(true);
        }

        return resultVO;
    }

    @Override
    public ResultVO<SeckillOrderDetailVO> getSeckillOrderDetailVOBySeckillOrderIdAndUsername(Long orderId, String username) {
        ResultVO<SeckillOrderDetailVO> resultVO = new ResultVO<>();

        SeckillOrderDetailVO orderDetailVO;
        Boolean hasKey = redisService.hasKey(orderId, SeckillOrderDetailVO.class);
        if (hasKey) {
            orderDetailVO = redisService.getById(orderId, SeckillOrderDetailVO.class);
        } else {
            orderDetailVO = seckillOrderDAO.getSeckillOrderDetailVOByIdAndUsername(orderId, username);
        }

        if (orderDetailVO == null) {
            resultVO.setErrorMsg("无权访问该订单!");
            return resultVO;
        } else {
            if (!hasKey) {
                redisService.setById(orderId, orderDetailVO);
            }
            resultVO.setSuccecssData(orderDetailVO);
            return resultVO;
        }
    }
}
