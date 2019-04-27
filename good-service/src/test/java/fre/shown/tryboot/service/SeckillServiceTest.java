package fre.shown.tryboot.service;

import fre.shown.tryboot.domain.SeckillOrderVO;
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
public class SeckillServiceTest {

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testCreateSeckillOrder() {
        SeckillOrderVO seckillOrderVO = new SeckillOrderVO();
        seckillOrderVO.setGoodCnt(1);
        seckillOrderVO.setDeliveryInfoId(1L);
        seckillOrderVO.setSeckillGoodId(1L);
        seckillOrderVO.setOrderChannel(1);
        seckillOrderVO.setUsername("radon");
        System.out.println(seckillService.createSeckillOrder(seckillOrderVO));
    }
}
