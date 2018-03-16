package com.fradou.nutrition.security.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.utils.work.ApiErrorMessage;

/**
 * Custom entryPoint for api calls
 * 
 * @author AF
 */
@Component
public class AuthenticationApiEntryPoint implements AuthenticationEntryPoint {


	
	@Override
	public void commence(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		
		ApiErrorMessage errorMessage = new ApiErrorMessage(401, "Please authenticate.");
		
	/**	try {
			ObjectMapper json = jackson2JsonObjectMapper.build();
			json.writeV
		}**/
	}
}
