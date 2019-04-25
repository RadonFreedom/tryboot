package fre.shown.tryboot.service;

import fre.shown.tryboot.dao.GoodDAO;
import fre.shown.tryboot.domain.SeckillGoodDTO;
import fre.shown.tryboot.domain.SeckillGoodVO;
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
    public SeckillGoodVO getSeckillGoodById(Long seckillGoodId) {
        SeckillGoodDTO seckillGoodDTO = goodDAO.getSeckillGoodById(seckillGoodId);
        SeckillGoodVO seckillGoodVO = new SeckillGoodVO(seckillGoodDTO);


        long seckillStartAt = seckillGoodVO.getStartDate().getTime();
        long seckillEndAt = seckillGoodVO.getEndDate().getTime();
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

        seckillGoodVO.setRemainSeconds(remainSeconds);
        return seckillGoodVO;
    }
}
