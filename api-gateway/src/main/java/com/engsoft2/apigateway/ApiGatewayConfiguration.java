package com.engsoft2.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/currency-exchange/**")
					.fillters(f->f.circuitBreaker(
						c->c.setName("circuitbreaker").setFallbackUri("forward:/open-circuit-breaker")))
					.uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-conversion/**")
					.fillters(f->f.circuitBreaker(
						c->c.setName("circuitbreaker").setFallbackUri("forward:/open-circuit-breaker")))
					.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-feign/**")
					.fillters(f->f.circuitBreaker(
						c->c.setName("circuitbreaker").setFallbackUri("forward:/open-circuit-breaker")))
					.uri("lb://currency-conversion"))
				.build();
	}    
}
