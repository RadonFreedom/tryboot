package fre.shown.tryboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author Radon Freedom
 * created at 2019.04.01 下午6:29
 */


@EnableAuthorizationServer
@EnableConfigurationProperties(Oauth2ServerClientsProperties.class)
@Configuration
public class Oauth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    private final AuthenticationManager authenticationManager;
    private final Oauth2ServerClientsProperties oauth2ServerClientsProperties;
    private final TokenStore tokenStore;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Oauth2AuthorizationServerConfig(AuthenticationManager authenticationManager, Oauth2ServerClientsProperties oauth2ServerClientsProperties, TokenStore tokenStore, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.oauth2ServerClientsProperties = oauth2ServerClientsProperties;
        this.tokenStore = tokenStore;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
                .tokenKeyAccess("permitAll()")
                //url:/oauth/check_token allow check token
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        //基于YML配置, 请在配置文件中添加相关配置
        InMemoryClientDetailsServiceBuilder inMemoryClientDetailsServiceBuilder = clients.inMemory();

        for (Oauth2ServerClientsProperties.Oauth2ClientProperties client : oauth2ServerClientsProperties.getClients().values()) {
            ClientDetailsServiceBuilder.ClientBuilder builder = inMemoryClientDetailsServiceBuilder
                    .withClient(client.getClientId())
                    .authorizedGrantTypes(client.getAuthorizedGrantTypes())
                    .authorities(client.getAuthorities())
                    .scopes(client.getScopes())
                    .autoApprove(client.getAutoApproveScopes())
                    .autoApprove(client.isAutoApprove())
                    .secret(passwordEncoder.encode(client.getSecret()))
                    .redirectUris(client.getRedirectUris())
                    .resourceIds(client.getResourceIds());
            //由于配置方法的参数是原始类型, 必须进行非空校验再传入
            if (client.getAccessTokenValiditySeconds() != null) {
                builder.accessTokenValiditySeconds(client.getAccessTokenValiditySeconds());
            }
            if (client.getRefreshTokenValiditySeconds() != null) {
                builder.refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds());
            }
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        endpoints
                .tokenStore(tokenStore)
                //注入authenticationManager来支持 password grant type
                .authenticationManager(authenticationManager);
    }

    @Configuration
    public static class NecessaryBeansConfig extends WebSecurityConfigurerAdapter {
        @Bean
        @Autowired
        public RedisTokenStore redisTokenStore(RedisConnectionFactory redisConnectionFactory) {
            return new RedisTokenStore(redisConnectionFactory);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }
}
