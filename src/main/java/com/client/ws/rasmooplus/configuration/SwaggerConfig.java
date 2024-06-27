package com.client.ws.rasmooplus.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Configuration
	public class RestTemplateConfig {

		//		@Bean
		//		public RestTemplate restTemplate() {
		//			return new RestTemplate();
		//		}
		//}

		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
					.groupName("v0")
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.client.ws.rasmooplus.controller"))
					.paths(PathSelectors.any())
					.build()
					.apiInfo(apiInfo());
		}

		private ApiInfo apiInfo() {
			ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

			return apiInfoBuilder.title("Rasmoo Plus")
					.description("Api para atender o client Rasmoo Plus")
					.version("0.0.1")
					.license("Rasmoo cursos de tecnologia")
					.build();
		}
	}
}
