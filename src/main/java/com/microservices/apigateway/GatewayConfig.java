package com.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("public_apis", r -> r
                        .path("/api/example")
                        .uri("http://example.com"))
                .route("nternal_apis", r -> r
                        .path("/api/another")
                        .uri("http://another-service.com"))
                .build();
    }
}
