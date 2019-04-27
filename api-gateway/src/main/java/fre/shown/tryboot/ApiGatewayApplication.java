package fre.shown.tryboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author Radon Freedom
 * created at 2019.03.09 20:35
 */

@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("r1", r -> r.host("**.baeldung.com")
                        .and()
                        .path("/baeldung")
                        .uri("http://baeldung.com"))
                .route(r -> r.host("**.baeldung.com")
                        .and()
                        .path("/myOtherRouting")
                        .filters(f -> f.prefixPath("/myPrefix"))
                        .uri("http://othersite.com")
                        .id("myOtherID"))
                .build();
    }
}
