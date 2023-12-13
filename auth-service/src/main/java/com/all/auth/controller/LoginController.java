package com.all.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.all.auth.payloads.LoginRequest;
import com.all.auth.service.LoginService;

@RestController
@RequestMapping("/api/v1")
public class LoginController<T> {
	
	@Autowired
	private LoginService<T> loginService;

	@PostMapping("/login")
	public T doLogin( @RequestBody LoginRequest request ) {
		
		Object login = this.loginService.login(request);
		return (T) login;
	}
	
}