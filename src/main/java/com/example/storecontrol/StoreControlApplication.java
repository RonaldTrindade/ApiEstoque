package com.example.storecontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StoreControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreControlApplication.class, args);
	}

}
