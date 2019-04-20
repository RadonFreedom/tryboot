package fre.shown.tryboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 因为{@link org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration ResourceServerConfiguration}
 * 已经覆盖了{@link WebSecurityConfigurerAdapter},
 * 所以我们只能用{@link ResourceServerConfigurerAdapter}来修改其安全配置.
 * @author Radon Freedom
 * created at 2019.03.25 下午7:27
 */


@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@Configuration
public class WebSecurityConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("auth/register")
                .permitAll();
    }
}
