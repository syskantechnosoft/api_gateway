package com.syskan.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routerBuilder(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route("hello-service", r -> r.path("/api/v1/hello/**").uri("http://localhost:8085/"))
				.route("welcome-service", r -> r.path("/api/v1/welcome/**").uri("http://localhost:8086/"))
				.route("user-service", r -> r.path("/users/**").uri("lb://user-service/"))
				.build();
	}

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//	    return builder.routes()
//	      .route("r1", r -> r.host("**.baeldung.com")
//	        .and()
//	        .path("/baeldung")
//	        .uri("http://baeldung.com"))
//	      .route(r -> r.host("**.baeldung.com")
//	        .and()
//	        .path("/myOtherRouting")
//	        .filters(f -> f.prefixPath("/myPrefix"))
//	        .uri("http://othersite.com")
//	        .id("myOtherID"))
//	    .build();

//	return builder.routes()
//			.route("user-service-route",
//					r->r.path("/api/users/**").filters(f->f.stripPrefix(1).addResponseHeader("X-Response-Time",LocalDateTime.now().toString())
//					.uri("lb://user-service"))
//			.route("asset-service-route",
//					r->r.path("/api/assets/**").filters(f->f.stripPrefix(1).addResponseHeader("X-Response-Time",LocalDateTime.now().toString())
//					.uri("lb://asset-service"))
//			.build();
//	}
}
