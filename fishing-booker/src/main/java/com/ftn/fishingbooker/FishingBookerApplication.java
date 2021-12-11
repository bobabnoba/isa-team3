package com.ftn.fishingbooker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.ftn.fishingbooker.repository","com.ftn.fishingbooker.service",
"com.ftn.fishingbooker.controller"})
@ComponentScan("**org**.ftn.fishingbooker")
public class FishingBookerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishingBookerApplication.class, args);
	}

}
