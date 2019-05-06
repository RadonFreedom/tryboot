package fre.shown.tryboot.service;

import fre.shown.tryboot.dao.GoodDAO;
import fre.shown.tryboot.dao.SeckillOrderDAO;
import fre.shown.tryboot.domain.ResultVO;
import fre.shown.tryboot.domain.order.SeckillGoodInfoDTO;
import fre.shown.tryboot.domain.order.SeckillOrderDO;
import fre.shown.tryboot.domain.order.SeckillOrderDTO;
import fre.shown.tryboot.domain.order.SeckillOrderDetailVO;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Radon Freedom
 * created at 2019.04.27 下午5:17
 */

@Service
public class OrderServiceImpl implements OrderService {

    private final SeckillOrderDAO seckillOrderDAO;
    private final GoodDAO goodDAO;
    private final RedisService redisService;

    public OrderServiceImpl(SeckillOrderDAO seckillOrderDAO, GoodDAO goodDAO, RedisConnectionFactory redisConnectionFactory, RedisService redisService) {
        this.seckillOrderDAO = seckillOrderDAO;
        this.goodDAO = goodDAO;
        this.redisService = redisService;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultVO<Long> createSeckillOrder(SeckillOrderDTO seckillOrderDTO) {

        ResultVO<Long> result = new ResultVO<>();
        Long id = seckillOrderDAO.isOrderExisted(seckillOrderDTO.getUsername(), seckillOrderDTO.getSeckillGoodId());
        if (id != null) {
            result.setData(id);
            result.setSuccessDataAndMsg(id, "您已经参加过该商品的秒杀!");
            return result;
        }

        if (!goodDAO.reduceSeckillGoodStock(seckillOrderDTO.getSeckillGoodId(), seckillOrderDTO.getGoodCnt())) {
            result.setErrorMsg("库存不足, 秒杀失败!");
            return result;
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

        seckillOrderDAO.addOrder(seckillOrderDO);

        result.setSuccecssData(seckillOrderDO.getId());
        return result;
    }

    @Override
    public ResultVO<SeckillOrderDetailVO> getSeckillOrderDetailVOBySeckillOrderIdAndUsername(Long orderId, String username) {
        ResultVO<SeckillOrderDetailVO> resultVO = new ResultVO<>();

        SeckillOrderDetailVO orderDetailVO;
        Boolean hasKey = redisService.hasKey(orderId, SeckillOrderDetailVO.class);
        if (hasKey) {
            orderDetailVO = redisService.get(orderId, SeckillOrderDetailVO.class);
        } else {
            orderDetailVO = seckillOrderDAO.getSeckillOrderDetailVOByIdAndUsername(orderId, username);
        }

        if (orderDetailVO == null) {
            resultVO.setErrorMsg("无权访问该订单!");
            return resultVO;
        } else {
            if (!hasKey) {
                redisService.set(orderId, orderDetailVO);
            }
            resultVO.setSuccecssData(orderDetailVO);
            return resultVO;
        }
    }
}
