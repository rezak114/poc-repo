package com.logme.card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.logme.card.repository" })
@ComponentScan(basePackages = { "com.logme.card.utils", "com.logme.card.service","com.logme.card.rest" })
public class CardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardApplication.class, args);
	}
}
