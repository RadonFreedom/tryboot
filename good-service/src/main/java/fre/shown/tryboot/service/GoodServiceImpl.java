package fre.shown.tryboot.service;

import fre.shown.tryboot.dao.GoodDAO;
import fre.shown.tryboot.domain.ResultVO;
import fre.shown.tryboot.domain.good.SeckillGoodDTO;
import fre.shown.tryboot.domain.good.SeckillGoodDetailVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Radon Freedom
 * created at 2019.04.22 下午8:18
 */

@Service
public class GoodServiceImpl implements GoodService {

    private final GoodDAO goodDAO;

    public GoodServiceImpl(GoodDAO goodDAO) {
        this.goodDAO = goodDAO;
    }

    @Override
    public List<SeckillGoodDTO> getSeckillGoodsAsList() {
        return goodDAO.getSeckillGoodsAsList();
    }

    @Override
    public ResultVO<SeckillGoodDetailVO> getSeckillGoodById(Long seckillGoodId) {

        ResultVO<SeckillGoodDetailVO> resultVO = new ResultVO<>();
        SeckillGoodDTO seckillGoodDTO = goodDAO.getSeckillGoodById(seckillGoodId);

        if (seckillGoodDTO == null || seckillGoodDTO.getId() == null) {
            resultVO.setErrorMsg("商品信息不存在!");
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
