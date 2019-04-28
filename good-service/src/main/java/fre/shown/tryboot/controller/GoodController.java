package fre.shown.tryboot.controller;

import fre.shown.tryboot.domain.ResultVO;
import fre.shown.tryboot.domain.good.SeckillGoodDTO;
import fre.shown.tryboot.domain.good.SeckillGoodDetailVO;
import fre.shown.tryboot.service.GoodService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Radon Freedom
 * created at 2019.04.22 下午7:28
 */


@RestController
public class GoodController {

    private final GoodService goodService;

    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping("/good")
    public List<SeckillGoodDTO> seckillGoods() {
        return goodService.getSeckillGoodsAsList();
    }

    @GetMapping("/good/{seckillGoodId}")
    public ResultVO<SeckillGoodDetailVO> seckillGood(@PathVariable Long seckillGoodId) {
        return goodService.getSeckillGoodById(seckillGoodId);
    }
}
