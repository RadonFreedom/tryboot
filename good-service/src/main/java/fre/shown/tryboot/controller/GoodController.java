package fre.shown.tryboot.controller;

import fre.shown.tryboot.dao.GoodDAO;
import fre.shown.tryboot.domain.SeckillGoodDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Radon Freedom
 * created at 2019.04.22 下午7:28
 */


@RestController
public class GoodController {

    private final GoodDAO goodDAO;

    public GoodController(GoodDAO goodDAO) {
        this.goodDAO = goodDAO;
    }

    @GetMapping("/goods")
    public List<SeckillGoodDTO> goods() {
        return goodDAO.getSeckillGoodsAsList();
    }

    @GetMapping("/goods/{seckillGoodId}")
    public SeckillGoodDTO good(@RequestParam Long seckillGoodId) {
        return goodDAO.getSeckillGoodById(seckillGoodId);
    }
}
