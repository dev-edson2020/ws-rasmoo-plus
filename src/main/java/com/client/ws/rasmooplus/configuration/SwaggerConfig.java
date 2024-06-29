package com.client.ws.rasmooplus.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class SwaggerConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Rasmoo Plus")
						.description("API para atender o cliente Rasmoo Plus")
						.version("0.0.1")
						.license(new License().name("Rasmoo cursos de tecnologia"))
						.contact(new Contact().name("Rasmoo").url("https://www.rasmoo.com").email("contact@rasmoo.com")));
	}
}
