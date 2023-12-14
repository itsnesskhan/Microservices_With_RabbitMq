package com.all.customer;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Fraud-Service")
public interface FraudClient {
	
	@GetMapping("/fraud-check/{customerId}")
	FrauCheckResponse isFraudenCustomer(@PathVariable Integer customerId, @RequestHeader(value = AUTHORIZATION, required = true) String token);

}
