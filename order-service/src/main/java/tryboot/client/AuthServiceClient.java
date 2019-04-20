package tryboot.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tryboot.domain.UserDTO;

/**
 * @author Radon Freedom
 * created at 2019.04.14 下午4:38
 */

@FeignClient("auth-service")
public interface AuthServiceClient {

    @GetMapping("auth/user/{username}")
    UserDTO userData(@PathVariable("username") String username);
}
