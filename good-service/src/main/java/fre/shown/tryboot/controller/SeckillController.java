package fre.shown.tryboot.controller;

import fre.shown.tryboot.domain.SeckillOrderVO;
import fre.shown.tryboot.service.SeckillService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Radon Freedom
 * created at 2019.04.20 下午7:18
 */

@RestController
public class SeckillController {

    private final SeckillService seckillService;

    public SeckillController(SeckillService seckillService) {
        this.seckillService = seckillService;
    }

    @PreAuthorize("#oauth2.hasScope('ui') and hasAnyAuthority('ROLE_USER')")
    @GetMapping("/seckill")
    public Boolean seckill(Principal principal, SeckillOrderVO seckillOrderVO) {

        seckillOrderVO.setUsername(principal.getName());

        return seckillService.createSeckillOrder(seckillOrderVO);
    }
}
