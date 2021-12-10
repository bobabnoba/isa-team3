package com.ftn.fishingbooker;

import com.ftn.fishingbooker.controller.ApplicationUserController;
import com.ftn.fishingbooker.repository.ApplicationUserRepository;
import com.ftn.fishingbooker.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages="com.ftn.fishingbooker.repository")
@EnableTransactionManagement
@EntityScan(basePackages="com.ftn.fishingbooker.service")
public class FishingBookerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishingBookerApplication.class, args);
	}

}
