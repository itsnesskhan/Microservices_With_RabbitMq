package com.all.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.all.auth.payloads.Customer;

public interface LoginRepository extends JpaRepository<Customer, String> {

	@Query(nativeQuery = true, value = "select * from customer u where u.email = :email")
	Customer findByEmail(String email);
	
}