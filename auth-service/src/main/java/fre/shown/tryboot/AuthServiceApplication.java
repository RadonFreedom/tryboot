package fre.shown.tryboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author Radon Freedom
 * created at 2019.04.02 上午8:43
 */

@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
