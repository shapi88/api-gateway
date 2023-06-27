package com.microservices.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("public_apis", r -> r
                        .path("/api/users")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://localhost:8081"))
                .route("internal_apis", r -> r
                        .path("/api/another")
                        .uri("http://another-service.com"))
                .build();
    }

    @Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
		return http.httpBasic().and()
				.csrf().disable()
				.build();
        }
}
