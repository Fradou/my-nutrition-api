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
	
	public NotBelongingToUserException() {
		super(getErrorMessage(null, null));
	}
	
	private static String getErrorMessage(Class<? extends GenericEntity> clazz, HttpMethod method) {
		
		if(method == HttpMethod.GET) {
			if(clazz != null) {
				return "You don't have authorization to access this resource (" + clazz.getSimpleName() + ")";
			}
			else {
				return "You don't have authorization to access this resource.";
			}
		}
		else {
			if(method != null && clazz != null) {
				return "You're not allowed to do this operation (" + method.toString() + ") on this resource (" + clazz.getSimpleName() + ")";
			}
			else {
				return "You don't have the required authorization for this operation.";
			}
		}
	}
}
