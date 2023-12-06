package com.all.fraud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/fraud-check")
@RequiredArgsConstructor
public class FraudController {

	@Autowired
	private FraudCheckService fraudCheckService;
	
	@GetMapping("/{customerId}")
	FrauCheckResponse isFraudenCustomer(@PathVariable Integer customerId) {
		Boolean isFraudCustomer = fraudCheckService.isFraudlentCustomer(customerId);
		log.info("fraud check request for customer id : {}",customerId);
		return FrauCheckResponse.builder().isFraudster(isFraudCustomer).build();
	}
	
}
