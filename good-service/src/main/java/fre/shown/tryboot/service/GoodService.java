package fre.shown.tryboot.service;

import fre.shown.tryboot.domain.SeckillGoodVO;
import fre.shown.tryboot.domain.SeckillGoodDTO;

import java.util.List;

/**
 * @author Radon Freedom
 * created at 2019.04.22 下午8:17
 */

public interface GoodService {

    List<SeckillGoodDTO> getSeckillGoodsAsList();

    SeckillGoodVO getSeckillGoodById(Long seckillGoodId);
}
