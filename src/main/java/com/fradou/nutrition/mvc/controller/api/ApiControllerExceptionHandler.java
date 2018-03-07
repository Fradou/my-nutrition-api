package com.fradou.nutrition.mvc.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fradou.nutrition.mvc.utils.exception.CannotCreateEntityException;

/**
 * Controller that will used to managed all generic exception on the API part of
 * the application.
 * 
 * @author AFT
 */
@RestControllerAdvice
public class ApiControllerExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CannotCreateEntityException.class)
	public String cannotCreateEntityException(CannotCreateEntityException ex) {
		return ex.getMessage();
	}
}
