package fre.shown.tryboot.controller;

import fre.shown.tryboot.client.AuthServiceClient;
import fre.shown.tryboot.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Radon Freedom
 * created at 2019.04.14 下午5:34
 */

@RestController
public class UserController {

    private final AuthServiceClient authServiceClient;

    public UserController(AuthServiceClient authServiceClient) {
        this.authServiceClient = authServiceClient;
    }

    @GetMapping("/")
    public User user() {
        return authServiceClient.user();
    }
}