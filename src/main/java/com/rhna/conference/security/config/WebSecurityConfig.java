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
			.authorizeRequests()

			//All Permit Access Control
			.antMatchers("/api/signup").permitAll()
			.antMatchers("/api/signin").permitAll()

			//Internal user registration access control
			.antMatchers("/backend-signup").hasAnyRole("ADMIN")

			//View All User Details Access Control
			.antMatchers("/api/get-all-users").hasRole("ADMIN")

			//research paper access control
			.antMatchers("/researchpaper/upload/").permitAll()
			.antMatchers("/researchpaper/downloadByUsername").hasAnyRole("ADMIN","REVIEWER")
			.antMatchers("/researchpaper/downloadById").hasAnyRole("ADMIN","REVIEWER")
			.antMatchers("/researchpaper/getResearchPaperDetailsByUsername/**").hasAnyRole("ADMIN","REVIEWER","USER_RESEARCHER")
			.antMatchers("/researchpaper/getAllResearchpapers").hasAnyRole("ADMIN","REVIEWER")
			.antMatchers("/researchpaper/updateStatus").hasAnyRole("ADMIN", "REVIEWER")
			.antMatchers("/researchpaper/updateFile").hasAnyRole("ADMIN","USER_RESEARCHER")
			.antMatchers("/researchpaper/deletePaper/**").hasAnyRole("ADMIN")

			// About Access Control
			.antMatchers("/about/add").hasAnyRole("ADMIN")
			.antMatchers("/about/get").permitAll()
			.antMatchers("/about/delete/**").hasAnyRole("ADMIN")
			.antMatchers("/about/update/**").hasAnyRole("ADMIN")
			.antMatchers("/about/get-id/**").hasAnyRole("ADMIN")

			// Download Access Control
			.antMatchers("/download/add").permitAll()
			.antMatchers("/download/get").permitAll()
			.antMatchers("/download/update-status").hasAnyRole("ADMIN")
			.antMatchers("/download/download-id-param").permitAll()
			.antMatchers("/download/update-file").permitAll()
			.antMatchers("/download/delete/**").hasAnyRole("ADMIN")
			.antMatchers("/download/download-id/**").permitAll()
			.antMatchers("/download/get-by-status/**").permitAll()

			// Keynote Access Control
			.antMatchers("/keynote/add").permitAll()
			.antMatchers("/keynote/get").permitAll()
			.antMatchers("/keynote/delete/**").hasAnyRole("ADMIN")
			.antMatchers("/keynote/update/**").permitAll()
			.antMatchers("/keynote/get-by-id/**").permitAll()
			.antMatchers("/keynote/get-by-status/**").permitAll()
			.antMatchers("/keynote/update-status/**").hasAnyRole("ADMIN")

			// News Access Control
			.antMatchers("/news/add").permitAll()
			.antMatchers("/news/get").permitAll()
			.antMatchers("/news/delete/**").hasAnyRole("ADMIN")
			.antMatchers("/news/update/**").permitAll()
			.antMatchers("/news/update-status/**").hasAnyRole("ADMIN")
			.antMatchers("/news/get-by-status/**").permitAll()
			.antMatchers("/news/get-by-id/**").permitAll()

             // News Access Control
			.antMatchers("/workshop/pending-workshops").hasAnyRole( "REVIEWER")
			.antMatchers("/workshop/approve").hasAnyRole( "REVIEWER")
			.antMatchers("/workshop/update").hasAnyRole( "ADMIN", "REVIEWER") //Reschedule approved / pending workshop
			.antMatchers("/workshop/delete").hasAnyRole("ADMIN")
			.antMatchers("/workshop/download-documents").permitAll()
			.antMatchers("/workshop/all-workshops").hasAnyRole("ADMIN")
			.antMatchers("/workshop/scheduled-workshops").permitAll()
			.antMatchers("/workshop/add").permitAll()
//			.antMatchers("/workshop/add").hasAnyRole("USER_WORKSHOP")

			.anyRequest().authenticated();


		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
