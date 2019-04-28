package fre.shown.tryboot.dao;

import fre.shown.tryboot.domain.order.SeckillOrderDO;
import fre.shown.tryboot.domain.order.SeckillOrderDetailVO;

/**
 * @author Radon Freedom
 * created at 2019.04.26 上午10:00
 */

public interface SeckillOrderDAO {

    Long isOrderExisted(String username, Long seckillGoodId);

    void addOrder(SeckillOrderDO seckillOrderDO);

    SeckillOrderDetailVO getSeckillOrderDetailVOByIdAndUsername(Long orderId, String username);
}
