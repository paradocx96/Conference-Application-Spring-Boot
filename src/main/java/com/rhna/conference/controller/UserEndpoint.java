package com.rhna.conference.controller;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhna.conference.api.UserApi;
import com.rhna.conference.dto.UserRegisterDto;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserEndpoint {
	
	@Autowired
	UserApi userApi;
	
	@PostMapping("/signup")
	public ResponseEntity<?> userRegister(@Valid @RequestBody UserRegisterDto userRegister) throws UnsupportedEncodingException {
		return userApi.registerUser(userRegister);	
	}
	
	@PostMapping("/backend-signup")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> backendUserRegister(@Valid @RequestBody UserRegisterDto userRegister) throws UnsupportedEncodingException {
		return userApi.registerUser(userRegister);	
	}
	

}
