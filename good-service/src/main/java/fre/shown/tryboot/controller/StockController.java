package fre.shown.tryboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Radon Freedom
 * created at 2019.04.24 下午9:21
 */


@RestController
public class StockController {


    @PreAuthorize("#oauth2.throwOnError(#oauth2.hasScope('server'))")
    @PostMapping("/stock/seckill")
    public Boolean reduceSeckillGoodStock(@RequestBody Long scekillGoodId) {

        return false;
    }
}
