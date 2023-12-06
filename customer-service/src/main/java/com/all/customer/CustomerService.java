package com.all.customer;

import java.util.List;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.all.rabbit.mq.RabbitMQMessageProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

	
	@Autowired
	private final RestTemplate restTemplate;
	
	@Autowired
	private final CustomerRepository customerRepository;
	
	@Autowired
	private final RabbitMQMessageProducer rabbitMQMessageProducer;

	Customer createCustomer(Customer customer) {
		//check if email is valid
		//check if email is not taken
		Customer saveCustomer = customerRepository.saveAndFlush(customer);
		//check if fraud
		FrauCheckResponse frauCheckResponse = restTemplate.getForObject("http://localhost:9091/fraud-check/{customerId}", FrauCheckResponse.class, saveCustomer.getId());
		
		if (frauCheckResponse.isFraudster()) {
			throw new IllegalStateException("He is Fraudster");
		}
		// send notification
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		
//		HttpEntity<NotificationRequest> httpEntity = new HttpEntity<>(
//				NotificationRequest.builder()
//				.toCustomerId(saveCustomer.getId())
//				.toCustomerEmail(saveCustomer.getEmail())
//				.message(String.format("Hi %s, welcome to Nessprivates...!", saveCustomer.getName()))
//				.build(),
//				httpHeaders
//				);
//		
//		restTemplate.postForLocation(
//				"http://localhost:8082/notification",
//				httpEntity
//				);
		
		//SENDING ASYNC NOTIFICATION WITH RABBITMQ
		NotificationRequest notificationRequest = NotificationRequest.builder()
		.toCustomerId(saveCustomer.getId())
		.toCustomerEmail(saveCustomer.getEmail())
		.message(String.format("Hi %s, welcome to Nessprivates...!", saveCustomer.getName()))
		.build();
		
		 rabbitMQMessageProducer.publish(
	                notificationRequest,
	                "internal.exchange",
	                "internal.notification.routing-key"
	        );
		
		return saveCustomer;
	}

	List<Customer> getAllCustomer() {
		List<Customer> allCustomer = customerRepository.findAll();
		return allCustomer;
	}

	Customer customerById(Integer id) {
		return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("customer not found with id : " + id));
	}

}