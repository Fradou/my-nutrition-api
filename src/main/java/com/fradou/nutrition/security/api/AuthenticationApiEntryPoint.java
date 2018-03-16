package com.fradou.nutrition.security.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fradou.nutrition.mvc.utils.work.ApiErrorMessage;

/**
 * Custom entryPoint for api calls
 * 
 * @author AF
 */
@Component
public class AuthenticationApiEntryPoint implements AuthenticationEntryPoint {

	
	private Jackson2ObjectMapperBuilder jackson2JsonObjectMapper;
	
	@Override
	public void commence(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		
	//	ApiErrorMessage errorMessage = new ApiErrorMessage(401, "Please authenticate.");
	/**	
		try {
			ObjectMapper mapper = jackson2JsonObjectMapper.build();
			String jsonResponse = mapper.writeValueAsString(errorMessage);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
			response.getWriter().write(jsonResponse);
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
        }**/
	}
}
