package fre.shown.tryboot.dao;

import fre.shown.tryboot.domain.good.SeckillGoodDTO;
import fre.shown.tryboot.domain.order.SeckillGoodInfoDTO;

import java.util.List;

/**
 * @author Radon Freedom
 * created at 2019.04.22 下午7:26
 */

public interface GoodDAO {

    List<SeckillGoodDTO> getSeckillGoodsAsList();

    SeckillGoodDTO getSeckillGoodById(Long seckillGoodId);

    Boolean reduceSeckillGoodStock(Long seckillGoodId, Integer goodCnt);

    Integer hasStock(Long seckillGoodId);

    SeckillGoodInfoDTO getSeckillGoodInfoById(Long seckillGoodId);
}
