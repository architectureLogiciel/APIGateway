package com.example.APIGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// Security Microservice
				.route("path_route",
						r -> r.path("/auth/**").and().method("GET","POST", "PUT", "DELETE").uri("http://localhost:3002"))
				// Forcast Microservice
				.route("path_route", r -> r.path("/products/**").and().method("GET").uri("http://localhost:3001")).build();
	}

}
