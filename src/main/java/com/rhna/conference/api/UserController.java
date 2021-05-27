package com.rhna.conference.api;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.rhna.conference.dal.model.ERole;
import com.rhna.conference.dal.model.Role;
import com.rhna.conference.dal.model.User;
import com.rhna.conference.dal.repository.RoleMongoRepository;
import com.rhna.conference.dal.repository.UserMongoRepository;
import com.rhna.conference.dto.MessageResponseDto;
import com.rhna.conference.dto.UserRegisterDto;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	

	@Autowired
	UserMongoRepository userRepository;

	@Autowired
	RoleMongoRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDto userRegister) throws UnsupportedEncodingException{
		
		
		//This is for check the program display correct values or not
		System.out.println(userRegister.getUsername());
		System.out.println(userRegister.getContactNo());
		System.out.println(userRegister.getEmail());
		System.out.println(userRegister.getPassword());
		System.out.println(userRegister.getUserType());
		
		
		// Create new user's account
		User user = new User(userRegister.getUsername(),
							 userRegister.getContactNo(),
							 passwordEncoder.encode(userRegister.getPassword()),
							 userRegister.getEmail(),
							 userRegister.getUserType());
		

		//Create new HashSet to store user Roles
		Set<Role> roles = new HashSet<>();

	
			//Check user is buyer or seller
		if(userRegister.getUserType().equals("user")) {
				
				//If it is true, Add ROLE_USER to that user
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
				
			}else if(userRegister.getUserType().equals("reviwer")){
				
				//If it is false, Add ROLE_REVIEWER to that user
				Role reviewerRole = roleRepository.findByName(ERole.ROLE_REVIEWER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(reviewerRole);
				
				}else if(userRegister.getUserType().equals("editor")){
				
					//If it is false, Add ROLE_EDITOR to that user
					Role editorRole = roleRepository.findByName(ERole.ROLE_EDITOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(editorRole);	
					
					}else {
						
						return ResponseEntity.badRequest().body(new MessageResponseDto("Please select valid role!"));
						
					}
			
	
		//set all roles to user object
		user.setRoles(roles);
		
		//Save all user details into the database
		userRepository.save(user);
		
		
		//return success MSG to frontEnd user is registered successfully
		return ResponseEntity.ok(new MessageResponseDto("User registered successfully!"));
	}
	
}