package fre.shown.tryboot.service;

import fre.shown.tryboot.domain.ResultVO;
import fre.shown.tryboot.domain.good.SeckillGoodDTO;
import fre.shown.tryboot.domain.good.SeckillGoodDetailVO;

import java.util.List;

/**
 * @author Radon Freedom
 * created at 2019.04.22 下午8:17
 */

public interface GoodService {

    List<SeckillGoodDTO> getSeckillGoodsAsList();

    ResultVO<SeckillGoodDetailVO> getSeckillGoodById(Long seckillGoodId);
}
