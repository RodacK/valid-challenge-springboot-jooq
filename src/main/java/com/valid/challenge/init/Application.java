package com.valid.challenge.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages="com.valid.challenge")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}