package fre.shown.tryboot.dao;

import fre.shown.tryboot.domain.SeckillGoodDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Radon Freedom
 * created at 2019.04.22 下午8:20
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodDAOTest {

    @Autowired
    private GoodDAO goodDAO;

    @Test
    public void testGoodDAO() {
        for (SeckillGoodDTO seckillGoodDTO : goodDAO.getSeckillGoodsAsList()) {
            System.out.println(seckillGoodDTO);
        }
        System.out.println(goodDAO.getSeckillGoodById(1L));
    }
}
