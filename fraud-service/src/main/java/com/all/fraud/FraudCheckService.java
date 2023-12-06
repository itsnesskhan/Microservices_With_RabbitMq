package com.all.fraud;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FraudCheckService {
	
	private final FaraudCheckRepository faraudCheckRepository;
	
	public Boolean isFraudlentCustomer(Integer customerId) {
		faraudCheckRepository.save(
				FraudCheckHistory.builder()
				.customer_id(customerId)
				.isFraudster(false)
				.build()
				);
		return false;
	}

}
