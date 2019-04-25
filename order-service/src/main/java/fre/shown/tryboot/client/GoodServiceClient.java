package fre.shown.tryboot.client;

import fre.shown.tryboot.domain.SeckillGoodDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Radon Freedom
 * created at 2019.04.14 下午4:38
 */

@FeignClient("good-service")
public interface GoodServiceClient {

    @GetMapping("/good/{seckillGoodId}")
    SeckillGoodDTO getSeckillGoodById(@PathVariable Long seckillGoodId);

    @PostMapping("/stock/seckill")
    Boolean reduceSeckillGoodStock(@RequestBody Long scekillGoodId);
}
