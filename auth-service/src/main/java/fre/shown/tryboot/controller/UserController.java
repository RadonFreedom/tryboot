package fre.shown.tryboot.controller;

import fre.shown.tryboot.domain.UserDO;
import fre.shown.tryboot.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Radon Freedom
 * created at 2019.04.19 下午8:29
 */

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("auth/user/{username}")
    @PreAuthorize("#oauth2.hasScope('server')")
    public UserDO userData(@PathVariable("username") String username) {
        return userService.getUserDataByUsername(username);
    }

    @PostMapping("auth/user/add")
    @PreAuthorize("permitAll()")
    public Boolean addUser(UserDO userDO) {
        return userService.addUser(userDO);
    }
}
