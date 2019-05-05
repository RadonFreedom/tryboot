package fre.shown.tryboot.service;

import fre.shown.tryboot.domain.order.SeckillOrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Radon Freedom
 * created at 2019.05.05 下午6:09
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    RedisService redisService;

    @Test
    public void testGetAndSet() {
        SeckillOrderDTO seckillOrderDTO = new SeckillOrderDTO();
        seckillOrderDTO.setSeckillGoodId(1L);
        seckillOrderDTO.setGoodCnt(1);
        seckillOrderDTO.setUsername("radon");
        seckillOrderDTO.setOrderChannel(1);
        seckillOrderDTO.setDeliveryInfoId(1L);


        redisService.set(seckillOrderDTO.getSeckillGoodId(), seckillOrderDTO);
        System.out.println(redisService.get(seckillOrderDTO.getSeckillGoodId(), SeckillOrderDTO.class));
    }
}
