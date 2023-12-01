package com.holovko.expertsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories
public class ExpertSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpertSystemApplication.class, args);
	}

}
