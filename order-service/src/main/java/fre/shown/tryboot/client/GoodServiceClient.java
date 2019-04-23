package fre.shown.tryboot.client;

import fre.shown.tryboot.domain.SeckillGoodDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Radon Freedom
 * created at 2019.04.22 下午6:58
 */

@FeignClient("good-service")
public interface GoodServiceClient {

    @GetMapping("good/user/{username}")
    SeckillGoodDTO userData(@PathVariable("username") String username);
}
