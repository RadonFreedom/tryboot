package fre.shown.tryboot.service;

import fre.shown.tryboot.dao.GoodDAO;
import fre.shown.tryboot.dao.SeckillOrderDAO;
import fre.shown.tryboot.domain.SeckillGoodInfoDTO;
import fre.shown.tryboot.domain.SeckillOrderDO;
import fre.shown.tryboot.domain.SeckillOrderVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Radon Freedom
 * created at 2019.04.26 上午9:14
 */

@Service
public class SeckillServiceImpl implements SeckillService {

    private final GoodDAO goodDAO;
    private final SeckillOrderDAO seckillOrderDAO;

    public SeckillServiceImpl(GoodDAO goodDAO, SeckillOrderDAO seckillOrderDAO) {
        this.goodDAO = goodDAO;
        this.seckillOrderDAO = seckillOrderDAO;
    }

    private Boolean doCreateSecKillOrder(SeckillOrderVO seckillOrderVO, SeckillGoodInfoDTO seckillGoodInfoDTO) {
        SeckillOrderDO seckillOrderDO = new SeckillOrderDO();
        seckillOrderDO.setUsername(seckillOrderVO.getUsername());
        seckillOrderDO.setSeckillGoodId(seckillOrderVO.getSeckillGoodId());
        seckillOrderDO.setDeliveryInfoId(seckillOrderVO.getDeliveryInfoId());
        seckillOrderDO.setOrderChannel(seckillOrderVO.getOrderChannel());
        seckillOrderDO.setGoodCount(seckillOrderVO.getGoodCnt());

        seckillOrderDO.setGoodName(seckillGoodInfoDTO.getGoodName());
        seckillOrderDO.setGoodPrice(seckillGoodInfoDTO.getSeckillPrice());
        seckillOrderDO.setStatus(0);

        seckillOrderDAO.addOrder(seckillOrderDO);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Boolean createSeckillOrder(SeckillOrderVO seckillOrderVO) {

        if (!seckillOrderDAO.isOrderExisted(seckillOrderVO.getUsername(), seckillOrderVO.getSeckillGoodId())
                && goodDAO.reduceSeckillGoodStock(seckillOrderVO.getSeckillGoodId(), seckillOrderVO.getGoodCnt())
        ) {
            SeckillGoodInfoDTO seckillGoodInfoDTO = goodDAO.getSeckillGoodInfoById(seckillOrderVO.getSeckillGoodId());
            return doCreateSecKillOrder(seckillOrderVO, seckillGoodInfoDTO);
        }
        return false;
    }
}
