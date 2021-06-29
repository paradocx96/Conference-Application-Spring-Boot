//Name : Malwatta H.G.
//ID : IT19240848
//Group : REG_WE_03

package com.rhna.conference.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhna.conference.api.UserApi;
import com.rhna.conference.dal.model.User;
import com.rhna.conference.dto.UserLoginDto;
import com.rhna.conference.dto.UserRegisterDto;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class UserEndpoint {
	
	@Autowired
	UserApi userApi;
	
	@PostMapping("/signup")
	public ResponseEntity<?> userRegister(@Valid @RequestBody UserRegisterDto userRegister) throws UnsupportedEncodingException, MessagingException {
		return userApi.registerUser(userRegister);	
	}
	
	@PostMapping("/backend-signup")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> backendUserRegister(@Valid @RequestBody UserRegisterDto userRegister) throws UnsupportedEncodingException, MessagingException {
		return userApi.registerUser(userRegister);	
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> userLogin(@Valid @RequestBody UserLoginDto userLogin){
		return userApi.authUserLogin(userLogin);
		
	}
	
	@GetMapping("/get-all-users")
	public List<User> getAllUser(){
		return userApi.getAllUserDetails();
	}
}
