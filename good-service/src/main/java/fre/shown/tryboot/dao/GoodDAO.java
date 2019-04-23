package fre.shown.tryboot.dao;

import fre.shown.tryboot.domain.SeckillGoodDTO;

import java.util.List;

/**
 * @author Radon Freedom
 * created at 2019.04.22 下午7:26
 */

public interface GoodDAO {

    SeckillGoodDTO getSeckillGoodById(Long seckillGoodId);
    List<SeckillGoodDTO> getSeckillGoodsAsList();
}
