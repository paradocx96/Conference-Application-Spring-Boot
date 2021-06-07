package com.rhna.conference.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rhna.conference.dal.adapter.UserDetailsServiceImpl;
import com.rhna.conference.security.jwt.AuthEntryPointJwt;
import com.rhna.conference.security.jwt.AuthTokenFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	AuthEntryPointJwt unauthorizedHandler;

	//Return new Authentication token
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	//Using Authentication Manager Builder, pass user details to builder
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

	//Return authentication Manager Bean and call parent class method 
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	//Created for Password Encode
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//set all backEnd URI to access accordingly
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/**").permitAll()
			
			//research paper access control
			.antMatchers("/researchpaper/upload/").permitAll()
			.antMatchers("/researchpaper/downloadByUsername").permitAll()
			.antMatchers("/researchpaper/downloadById").permitAll()
			.antMatchers("/researchpaper/getResearchPaperDetailsByUsername/**").permitAll()
			.antMatchers("/researchpaper/getAllResearchpapers").permitAll()
			.antMatchers("/researchpaper/updateStatus").permitAll()
			.antMatchers("/researchpaper/updateFile").permitAll()
			.antMatchers("/researchpaper/deletePaper/**").hasAnyRole("ADMIN")

			// About Access Control
			.antMatchers("/about/add").permitAll()
			.antMatchers("/about/get").permitAll()
			.antMatchers("/about/delete/**").permitAll()
			.antMatchers("/about/update/**").permitAll()
			.antMatchers("/about/get-id/**").permitAll()

			// Download Access Control
			.antMatchers("/download/add").permitAll()
			.antMatchers("/download/get").permitAll()
			.antMatchers("/download/update-status").permitAll()
			.antMatchers("/download/download-id-param").permitAll()
			.antMatchers("/download/update-file").permitAll()
			.antMatchers("/download/delete/**").permitAll()
			.antMatchers("/download/download-id/**").permitAll()
			.antMatchers("/download/get-by-status/**").permitAll()

			.anyRequest().authenticated();
			
		   
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
