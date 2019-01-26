package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SignApplication.class, args);
	}
}
