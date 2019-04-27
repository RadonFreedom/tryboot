package fre.shown.tryboot.dao;

import fre.shown.tryboot.domain.SeckillOrderDO;

/**
 * @author Radon Freedom
 * created at 2019.04.26 上午10:00
 */

public interface SeckillOrderDAO {

    Boolean isOrderExisted(String username, Long seckillGoodId);

    void addOrder(SeckillOrderDO seckillOrderDO);
}
