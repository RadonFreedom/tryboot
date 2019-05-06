package fre.shown.tryboot.controller;

import fre.shown.tryboot.domain.ResultVO;
import fre.shown.tryboot.domain.order.SeckillOrderDTO;
import fre.shown.tryboot.domain.order.SeckillOrderDetailVO;
import fre.shown.tryboot.service.OrderService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Radon Freedom
 * created at 2019.04.27 下午4:46
 */

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("#oauth2.hasScope('ui') and hasAnyAuthority('ROLE_USER')")
    @PostMapping("/seckill")
    public ResultVO<Long> seckill(Principal principal, SeckillOrderDTO seckillOrderDTO) {

        seckillOrderDTO.setUsername(principal.getName());
        ResultVO<Long> result;
        try {
            result = orderService.createSeckillOrder(seckillOrderDTO);
            return result;
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            result = new ResultVO<>();
            result.setErrorMsg("秒杀失败, 您可能在其他地方登录并参与了秒杀!");
            return result;
        }
    }

    @PreAuthorize("#oauth2.hasScope('ui') and hasAnyAuthority('ROLE_USER')")
    @GetMapping("/order/{orderId}")
    public ResultVO<SeckillOrderDetailVO> seckillOrder(Principal principal, @PathVariable Long orderId) {

        return orderService.getSeckillOrderDetailVOBySeckillOrderIdAndUsername(orderId, principal.getName());
    }
}
