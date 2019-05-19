package fre.shown.tryboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Radon Freedom
 * created at 2019.05.19 10:50
 */


@Configuration
public class GatewayConfig {


    @Bean
    public IpAddressKeyResolver ipAddressKeyResolver() {
        return new IpAddressKeyResolver();
    }
}
