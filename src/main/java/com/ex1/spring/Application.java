package com.ex1.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	// RUN "./setup-java21.sh" IN CONSOLE BEFORE RUNNING THIS APPLICATION
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
