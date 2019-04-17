package fre.shown.tryboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Radon Freedom
 * created at 2019.04.17 下午8:23
 */

@EnableWebSecurity(debug = true)
@Configuration
public class Oauth2ServerConfig extends ResourceServerConfigurerAdapter {

}
