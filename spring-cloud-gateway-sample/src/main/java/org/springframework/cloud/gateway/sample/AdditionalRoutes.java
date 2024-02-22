package org.springframework.cloud.gateway.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/2/21 20:17
 */
@Configuration(proxyBeanMethods = false)
public class AdditionalRoutes {

	@Value("${test.uri:http://httpbin.org:80}")
	private String uri;

	@Bean
	public RouteLocator additionalRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.host("kotlin.abc.org")
						.and()
						.path("/image/png")
						.filters(f -> f.prefixPath("/httpbin")
								.addResponseHeader("X-TestHeader", "foobar"))
						.uri(uri))
				.build();
	}


}
