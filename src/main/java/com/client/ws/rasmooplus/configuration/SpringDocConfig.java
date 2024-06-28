package com.client.ws.rasmooplus.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SpringDocConfig {
//	@Bean
//	public OpenAPI apiInfo() {
//		return new OpenAPI().info(new Info()
//				.title("RasPlusApi")
//				.version("V1"));
//	}

	@Bean
	public GroupedOpenApi httpApi() {
		return GroupedOpenApi.builder()
				.group("http")
				.pathsToMatch("/**")
				.build();
	}
}