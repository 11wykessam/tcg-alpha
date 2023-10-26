package com.wykessam.tsgalpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableMongoRepositories
public class TsgalphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TsgalphaApplication.class, args);
	}

}
