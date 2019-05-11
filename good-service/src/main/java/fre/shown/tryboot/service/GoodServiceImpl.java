package fre.shown.tryboot.service;

import fre.shown.tryboot.dao.GoodDAO;
import fre.shown.tryboot.domain.ResultVO;
import fre.shown.tryboot.domain.good.SeckillGoodDTO;
import fre.shown.tryboot.domain.good.SeckillGoodDetailVO;
import fre.shown.tryboot.service.redis.RedisService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Radon Freedom
 * created at 2019.04.22 下午8:18
 */

@Service
public class GoodServiceImpl implements GoodService {

    private final GoodDAO goodDAO;
    private final RedisService redisService;

    public GoodServiceImpl(GoodDAO goodDAO, RedisService redisService) {
        this.goodDAO = goodDAO;
        this.redisService = redisService;
    }

    @Override
    public List<SeckillGoodDTO> getSeckillGoodsAsList() {
        return goodDAO.getSeckillGoodsAsList();
    }

    @Override
    public ResultVO<SeckillGoodDetailVO> getSeckillGoodById(Long seckillGoodId) {

        ResultVO<SeckillGoodDetailVO> resultVO = new ResultVO<>();



        Boolean hasKey = redisService.hasKey(seckillGoodId, SeckillGoodDTO.class);
        SeckillGoodDTO seckillGoodDTO;
        if (hasKey) {
            seckillGoodDTO = redisService.get(seckillGoodId, SeckillGoodDTO.class);
        }  else {
            seckillGoodDTO = goodDAO.getSeckillGoodById(seckillGoodId);
        }

        if (seckillGoodDTO == null) {
            resultVO.setErrorMsg("商品信息不存在!");
            return resultVO;
        } else if (!hasKey) {
            redisService.set(seckillGoodId, seckillGoodDTO);
        }

        SeckillGoodDetailVO seckillGoodDetailVO = new SeckillGoodDetailVO(seckillGoodDTO);

        long seckillStartAt = seckillGoodDetailVO.getStartDate().getTime();
        long seckillEndAt = seckillGoodDetailVO.getEndDate().getTime();
        long now = System.currentTimeMillis();
        long remainSeconds;

        if (now < seckillStartAt) {
            //秒杀还没开始，倒计时
            remainSeconds = seckillStartAt - now;
        } else if (now > seckillEndAt) {
            //秒杀已经结束
            remainSeconds = -1L;
        } else {
            //秒杀进行中
            remainSeconds = 0L;
        }

        seckillGoodDetailVO.setRemainSeconds(remainSeconds);
        resultVO.setSuccecssData(seckillGoodDetailVO);
        return resultVO;
    }
}
