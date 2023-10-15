package com.wykessam.tsgalpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TsgalphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TsgalphaApplication.class, args);
	}

}
