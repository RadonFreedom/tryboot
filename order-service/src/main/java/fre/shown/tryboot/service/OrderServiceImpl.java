package fre.shown.tryboot.service;

import fre.shown.tryboot.client.GoodServiceClient;
import fre.shown.tryboot.domain.DeliveryInfoDO;
import fre.shown.tryboot.domain.GoodDO;
import fre.shown.tryboot.domain.OrderDO;
import org.springframework.stereotype.Service;

/**
 * @author Radon Freedom
 * created at 2019.04.24 下午8:45
 */

@Service
public class OrderServiceImpl implements OrderService {

    private final GoodServiceClient goodServiceClient;

    public OrderServiceImpl(GoodServiceClient goodServiceClient) {
        this.goodServiceClient = goodServiceClient;
    }

    protected OrderDO createOrder(Integer goodCnt, GoodDO goodDO, DeliveryInfoDO deliveryInfoDO, Integer orderChannel) {
        OrderDO orderDO = new OrderDO();
        orderDO.setUserId(deliveryInfoDO.getUserId());
        orderDO.setGoodId(goodDO.getId());
        orderDO.setDeliveryAddrId(deliveryInfoDO.getId());
        orderDO.setGoodName(goodDO.getGoodName());
        orderDO.setGoodCount(1);
        orderDO.setGoodPrice(goodDO.getGoodPrice());
        orderDO.setOrderChannel(orderChannel);
        orderDO.setStatus(0);

        return orderDO;
    }
//TODO
    public OrderService
}
