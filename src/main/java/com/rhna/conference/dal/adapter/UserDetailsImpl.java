package com.rhna.conference.dal.adapter;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rhna.conference.dal.model.User;


public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private int contactNo;
	private String email;

	@JsonIgnore
	private String password;
	

	private Collection<? extends GrantedAuthority> authorities;
	
	//Overloaded constructor
	public UserDetailsImpl(String id, String username, int contactNo, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.contactNo = contactNo;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	//Get user details and return new userDetailImple object
	public static UserDetailsImpl build(User user) {
		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
		
		
		return new UserDetailsImpl(user.getId(), 
								   user.getUsername(),
								   user.getContactNo(),
								   user.getEmail(),
								   user.getPassword(), 
								   authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean equals(Object object) {
		
		if (this == object) {
			return true;
		}
		
		if (object == null || getClass() != object.getClass()) {
			return false;	
		}
			
		UserDetailsImpl user = (UserDetailsImpl) object;
		
		return Objects.equals(id, user.id);
	}
	
	//Getters and Setters

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	
	public int getContactNo() {
		return contactNo;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
