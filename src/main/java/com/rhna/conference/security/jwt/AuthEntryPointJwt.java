//Name : Malwatta H.G.
//ID : IT19240848
//Group : REG_WE_03

package com.rhna.conference.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	//implement for catch exception when authentication error arise 
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		logger.error("Unauthorized error: {}", authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sorry! You can't access this page!");
		
	}

}
