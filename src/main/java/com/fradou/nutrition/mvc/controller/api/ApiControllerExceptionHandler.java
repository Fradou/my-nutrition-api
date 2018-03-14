package com.fradou.nutrition.mvc.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fradou.nutrition.mvc.utils.exception.InvalidDataCreationException;
import com.fradou.nutrition.mvc.utils.exception.NotBelongingToUserException;
import com.fradou.nutrition.mvc.utils.work.ApiErrorMessage;

/**
 * Controller that will used to managed all generic exception on the API part of
 * the application.
 * 
 * @author AF
 */
@RestControllerAdvice("com.fradou.nutrition.mvc.controller.api")
public class ApiControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiControllerExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidDataCreationException.class)
	public ApiErrorMessage cannotCreateEntityException(InvalidDataCreationException ex) {
		return new ApiErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ApiErrorMessage cannotReadHttpMessageException(HttpMessageNotReadableException ex) {
		return new ApiErrorMessage(HttpStatus.BAD_REQUEST.value(), "Data error");
	}
	
	/**
	 * "Bad" catch all for all missed exception. Will avoid uncatched 500 and allow better debugging 
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ApiErrorMessage catchAllException(Exception ex) {
		LOGGER.error("Exception not catched : " + ex.getMessage(), ex);
		return new ApiErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unknown error has occured. Check sent datas and retry.");
	}
	
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(NotBelongingToUserException.class)
	public ApiErrorMessage notBelongingException(NotBelongingToUserException ex) {
		return new ApiErrorMessage(HttpStatus.FORBIDDEN.value(), ex.getMessage());
	}	
}
