package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoAuditing
@EnableReactiveMongoRepositories
public class MicroServiceReactiveSpring5SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceReactiveSpring5SampleApplication.class, args);
	}
}
