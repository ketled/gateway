package com.example.gateway;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("first", r -> r.path("/mybooking/gateway/project-one/**")
                        .uri("http://localhost:8800/mybooking"))
                .route("second", r -> r.path("/mybooking/gateway/project-two/**")
                        .uri("lb://MYBOOKING-PROJECT-TWO/mybooking/project-two"))
                .build();
    }
}
