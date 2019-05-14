package fre.shown.tryboot.controller;

import fre.shown.tryboot.domain.ResultVO;
import fre.shown.tryboot.domain.order.SeckillOrderDTO;
import fre.shown.tryboot.domain.order.SeckillOrderDetailVO;
import fre.shown.tryboot.service.OrderService;
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
@PreAuthorize("#oauth2.hasScope('ui') and hasAnyAuthority('ROLE_USER')")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/seckill/verifyCode")
    public ResultVO<String> seckillVerifyCode(Principal principal, Long seckillGoodId) {
        return orderService.getSeckillVerifyCode(principal.getName(), seckillGoodId);
    }

    @PostMapping("/seckill/path")
    public ResultVO<String> seckillPath(Principal principal, Long seckillGoodId, String verifyCode) {
        return orderService.getSeckillPath(principal.getName(), seckillGoodId, verifyCode);
    }

    @PostMapping("/seckill/{path}")
    public ResultVO<Object> seckill(Principal principal, @PathVariable("path") String path, SeckillOrderDTO seckillOrderDTO) {

        seckillOrderDTO.setUsername(principal.getName());
        return orderService.trySeckill(seckillOrderDTO, path);
    }

    @PostMapping("/seckill/result")
    public ResultVO<Long> seckillResult(Principal principal, Long seckillGoodId) {
        return orderService.getSeckillResult(principal.getName(), seckillGoodId);
    }

    @GetMapping("/order/{orderId}")
    public ResultVO<SeckillOrderDetailVO> seckillOrder(Principal principal, @PathVariable Long orderId) {
        return orderService.getSeckillOrderDetailVOBySeckillOrderIdAndUsername(orderId, principal.getName());
    }
}
