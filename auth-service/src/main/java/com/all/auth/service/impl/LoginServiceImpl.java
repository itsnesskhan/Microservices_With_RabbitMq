package com.all.auth.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.all.auth.payloads.Customer;
import com.all.auth.payloads.LoginRequest;
import com.all.auth.payloads.LoginResponse;
import com.all.auth.repo.LoginRepository;
import com.all.auth.service.LoginService;
import com.all.security.library.jwt.JwtTokenUtil;
import com.all.security.library.payloads.CommonResponse;

@Service
public class LoginServiceImpl<T> implements LoginService<T> {

	@Autowired
	private LoginRepository loginRepository;
	
	private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public T login(LoginRequest request) {
		if(request.getEmail() == null || request.getPassword() == null ) {
			logger.info("LoginService :  UserName and Password Can't be empty!!");
			return (T) new CommonResponse("UserName and Password Can't be empty!!", 400 );	 
		}
		
		Customer user = this.loginRepository.findByEmail(request.getEmail());
		logger.info("LoginService : Login User : {}", user);
		
		if(user == null) {
			logger.info("LoginService : User not found !!");
			return (T) new CommonResponse("User not found!!", 404);
		}
		
		if(!user.getPassword().equals(request.getPassword())) {
			logger.info("LoginService : Password mismatch !!");
			return (T) new CommonResponse("invalid Password", 404);
		}
		
		logger.info("LoginService : Password matched !!");
		UserDetails userDetails= new org.springframework.security.core.userdetails.User(request.getEmail(), request.getPassword(), true, true, true, true, new ArrayList<>());
		
		String token = this.jwtTokenUtil.generateToken(userDetails);
		logger.info("LoginService : login Success : {}", user);
		
		
		return (T) new LoginResponse(token, user);
		
	}
}
