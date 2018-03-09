package com.fradou.nutrition.mvc.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fradou.nutrition.mvc.utils.exception.InvalidDataCreationException;

/**
 * Controller that will used to managed all generic exception on the API part of
 * the application.
 * 
 * @author AFT
 */
@RestControllerAdvice("com.fradou.nutrition.mvc.controller.api")
public class ApiControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiControllerExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidDataCreationException.class)
	public String cannotCreateEntityException(InvalidDataCreationException ex) {
		return ex.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public String cannotReadHttpMessageException(HttpMessageNotReadableException ex) {
		return "Data error";
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String catchAllException(Exception ex) {
		LOGGER.error("Exception not catched : " + ex.getMessage());
		return "An unknown error has occured. Check sent datas and retry.";
	}
	
}
