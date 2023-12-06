package com.all.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class Config {
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
