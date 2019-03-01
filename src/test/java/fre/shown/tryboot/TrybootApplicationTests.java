package fre.shown.tryboot;

import fre.shown.tryboot.bean.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrybootApplicationTests {

    @Autowired
    private Account account;

    @Test
    public void contextLoads() {

        System.out.println(account);
    }

}
