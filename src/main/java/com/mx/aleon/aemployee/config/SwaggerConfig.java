package com.mx.aleon.aemployee.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("public").pathsToMatch("/api/**").build();
	}

	@Bean
	io.swagger.v3.oas.models.OpenAPI customOpenAPI() {
		return new io.swagger.v3.oas.models.OpenAPI().info(new Info().title("Employee Management API").version("1.0")
				.description("API Documentation for Employee Management System"));
	}
}
