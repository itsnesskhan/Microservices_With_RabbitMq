package com.all.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

	@Autowired
	private final CustomerService customerService;
	
	@PostMapping
	ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
	  customer = customerService.createCustomer(customer);
	  log.info("saved customer : {}",customer);
	  return ResponseEntity.created(null).body(customer);
	}
	
	@GetMapping
	ResponseEntity<List<Customer>> getAllCustomer(){
	  List<Customer> allCustomer = customerService.getAllCustomer();
	  return ResponseEntity.ok(allCustomer);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<Customer> getCustomerById(@PathVariable Integer id){
	  Customer customer = customerService.customerById(id);
	  return ResponseEntity.ok(customer);
	}
}
