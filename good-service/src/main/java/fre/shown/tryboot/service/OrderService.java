package fre.shown.tryboot.service;

import fre.shown.tryboot.domain.ResultVO;
import fre.shown.tryboot.domain.order.SeckillOrderDTO;
import fre.shown.tryboot.domain.order.SeckillOrderDetailVO;

/**
 * @author Radon Freedom
 * created at 2019.04.27 下午5:16
 */

public interface OrderService {


    ResultVO<String> getSeckillVerifyCode(String username, Long seckillGoodId);

    ResultVO<String> getSeckillPath(String username, Long seckillGoodId, String verifyCode);

    ResultVO<Object> trySeckill(SeckillOrderDTO seckillOrderDTO, String path);

    ResultVO<Long> getSeckillResult(String username, Long seckillGoodId);

    ResultVO<SeckillOrderDetailVO> getSeckillOrderDetailVOBySeckillOrderIdAndUsername(Long orderId, String username);

}
