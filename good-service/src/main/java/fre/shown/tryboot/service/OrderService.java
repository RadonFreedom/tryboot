package fre.shown.tryboot.service;

import fre.shown.tryboot.domain.ResultVO;
import fre.shown.tryboot.domain.order.SeckillOrderDTO;
import fre.shown.tryboot.domain.order.SeckillOrderDetailVO;

/**
 * @author Radon Freedom
 * created at 2019.04.27 下午5:16
 */

public interface OrderService {

    ResultVO<SeckillOrderDetailVO> getSeckillOrderDetailVOBySeckillOrderIdAndUsername(Long orderId, String username);

    ResultVO<Long> createSeckillOrder(SeckillOrderDTO seckillOrderDTO);
}
