package fre.shown.tryboot.service;

import fre.shown.tryboot.domain.order.SeckillOrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Radon Freedom
 * created at 2019.04.26 下午7:41
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testorderService() {
        SeckillOrderDTO seckillOrderDTO = new SeckillOrderDTO();
        seckillOrderDTO.setGoodCnt(1);
        seckillOrderDTO.setDeliveryInfoId(1L);
        seckillOrderDTO.setSeckillGoodId(1L);
        seckillOrderDTO.setOrderChannel(1);
        seckillOrderDTO.setUsername("radon");
        System.out.println(orderService.createSeckillOrder(seckillOrderDTO));
        System.out.println(orderService.createSeckillOrder(seckillOrderDTO));
        System.out.println(orderService.getSeckillOrderDetailVOBySeckillOrderIdAndUsername(1L, "radon"));
        System.out.println(orderService.getSeckillOrderDetailVOBySeckillOrderIdAndUsername(1L, ""));
    }
}
