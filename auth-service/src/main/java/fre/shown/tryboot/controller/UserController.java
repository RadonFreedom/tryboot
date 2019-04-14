package fre.shown.tryboot.controller;

import fre.shown.tryboot.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Radon Freedom
 * created at 2019.04.14 下午5:27
 */

@RestController
public class UserController {
    @PreAuthorize("#oauth2.hasScope('server')")
    @GetMapping("/user")
    public User user() {

        return new User("radon@gmail.com", "radon");
    }
}
