package com.library.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Libary Application API").version("1.0")
				.description("This application should provide a REST API that satisfies the following requirements.\r\n"
						+ "\r\n"
						+ "a) returns all users who have actually borrowed at least one book\r\n"
						+ "b) returns all non-terminated users who have not currently borrowed anything\r\n"
						+ "c) returns all users who have borrowed a book on a given date\r\n"
						+ "d) returns all books borrowed by a given user in a given date range\r\n"
						+ "e) returns all available (not borrowed) books\r\n"
						+ "\r\n"
						+ "as input you will get three csv files containing all users, books and who borrowed what and when"));
	}
}
