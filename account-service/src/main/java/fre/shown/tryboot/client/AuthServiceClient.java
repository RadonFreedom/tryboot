package fre.shown.tryboot.client;

import fre.shown.tryboot.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Radon Freedom
 * created at 2019.04.14 下午4:38
 */

@FeignClient("auth-service")
public interface AuthServiceClient {

    @GetMapping("/user")
    User user();
}
