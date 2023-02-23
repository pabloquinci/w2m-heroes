package com.w2m.service;

import org.springframework.http.ResponseEntity;

import com.w2m.dto.JwtResponse;
import com.w2m.dto.LoginRequest;
import com.w2m.dto.SignupRequest;

public interface UserService {
	
	public JwtResponse login(LoginRequest request);
	
	public ResponseEntity registrarse(SignupRequest request);

}
