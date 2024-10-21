package com.easyhostel.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.easyhostel.backend")
@EnableAsync
@EnableJpaRepositories(basePackages = "com.easyhostel.backend.domain.repository.interfaces")
public class EasyHostelApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyHostelApplication.class, args);
	}

}
