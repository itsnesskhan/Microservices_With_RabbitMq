package com.all.fraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(
		scanBasePackages = {
				"com.all.fraud",
				"com.all.security.library"
		})
public class FraudServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FraudServiceApplication.class, args);
	}

}
