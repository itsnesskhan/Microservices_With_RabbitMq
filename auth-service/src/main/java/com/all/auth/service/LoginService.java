package com.all.auth.service;

import com.all.auth.payloads.LoginRequest;

public interface LoginService<T> {

	T login(LoginRequest request);
	
}