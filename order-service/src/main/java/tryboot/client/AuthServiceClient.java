package tryboot.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tryboot.domain.UserDTO;

/**
 * @author Radon Freedom
 * created at 2019.04.14 下午4:38
 */

@FeignClient("auth-service")
public interface AuthServiceClient {

    @GetMapping("/user")
    UserDTO user();
}
