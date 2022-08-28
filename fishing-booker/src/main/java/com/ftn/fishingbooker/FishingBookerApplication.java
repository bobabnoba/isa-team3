package com.ftn.fishingbooker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FishingBookerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishingBookerApplication.class, args);
	}

}
