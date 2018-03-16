package com.fradou.nutrition.mvc.utils.exception;

import org.springframework.http.HttpMethod;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;

/**
 * Custom exception for all calls on an entity that doesn't "belong" to calling user.
 * @author AF
 *
 */
public class NotBelongingToUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotBelongingToUserException(Class<? extends GenericEntity> clazz, HttpMethod method) {
		super(getErrorMessage(clazz, method));
	}
	
	private static String getErrorMessage(Class<? extends GenericEntity> clazz, HttpMethod method) {
		
		if(method == HttpMethod.GET) {
			return "You don't have authorization to access this resource (" + clazz.getSimpleName() + ")";
		}
		else {
			return "You're not allowed to do any operation on this resource (" + clazz.getSimpleName() + ")";
		}
	}
}
