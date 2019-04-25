package fre.shown.tryboot.controller;

import fre.shown.tryboot.domain.OrderDO;
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

    @PreAuthorize("#oauth2.hasScope('ui') and hasAnyAuthority('ROLE_USER')")
    @GetMapping("/seckill")
    public OrderDO seckill(Principal principal) {

    }
}
