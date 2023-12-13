package com.all.customer;

import java.util.List;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.all.rabbit.mq.RabbitMQMessageProducer;
import com.all.security.library.payloads.SharedData;

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
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.AUTHORIZATION, SharedData.getSharedDataMap().get("jwtToken"));
		HttpEntity<Object> entity = new HttpEntity<>(null, httpHeaders);
		
		 ResponseEntity<FrauCheckResponse> responseEntity = restTemplate.exchange(
				 	"http://localhost:9091/fraud-check/{customerId}",
	                HttpMethod.GET,
	                entity,
	                FrauCheckResponse.class,
	                customer.getId());
		
		if (responseEntity.getBody().isFraudster()) {
			throw new IllegalStateException("He is Fraudster");
		}
//		 send notification
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<NotificationRequest> httpEntity = new HttpEntity<>(
//				NotificationRequest.builder()
//				.toCustomerId(saveCustomer.getId())
//				.toCustomerEmail(saveCustomer.getEmail())
//				.message(String.format("Hi %s, welcome to abcprivates...!", saveCustomer.getName()))
//				.build(),
//				httpHeaders
//				);
//		
//		restTemplate.postForLocation(
//				"http://localhost:8082/notification",
//				httpEntity
//				);
		
//		//SENDING ASYNC NOTIFICATION WITH RABBITMQ
		NotificationRequest notificationRequest = NotificationRequest.builder()
		.toCustomerId(saveCustomer.getId())
		.toCustomerEmail(saveCustomer.getEmail())
		.message(String.format("Hi %s, welcome to abcprivates...!", saveCustomer.getName()))
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
