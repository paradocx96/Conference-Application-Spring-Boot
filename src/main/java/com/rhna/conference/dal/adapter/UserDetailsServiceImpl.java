//Name : Malwatta H.G.
//ID : IT19240848
//Group : REG_WE_03

package com.rhna.conference.dal.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rhna.conference.dal.model.User;
import com.rhna.conference.dal.repository.UserMongoRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserMongoRepository userRepository;
	
	//Find the username in the database if not throw custom exception error
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));

		return UserDetailsImpl.build(user);
	}

	public List<User> getAllUserDetails() {
		// TODO Auto-generated method stub
		return new ArrayList<>(userRepository.findAll());
	}

	
}
