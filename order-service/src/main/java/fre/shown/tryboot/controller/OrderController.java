package fre.shown.tryboot.controller;

import fre.shown.tryboot.client.AuthServiceClient;
import fre.shown.tryboot.domain.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Radon Freedom
 * created at 2019.04.20 下午7:18
 */

@RestController
public class OrderController {

    private final AuthServiceClient authServiceClient;

    public OrderController(AuthServiceClient authServiceClient) {
        this.authServiceClient = authServiceClient;
    }

    @PreAuthorize("#oauth2.hasScope('ui') and hasAnyAuthority('ROLE_USER')")
    @GetMapping("/order/current")
    public UserDTO test(Principal principal) {
        return authServiceClient.userData(principal.getName());
    }
}
