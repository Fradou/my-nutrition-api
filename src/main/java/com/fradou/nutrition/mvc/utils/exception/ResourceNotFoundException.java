package com.fradou.nutrition.mvc.utils.exception;

/**
 * Custom exception for all Rest call with bad id.
 * 
 * @author AF
 *
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(int id) {
		super("Couldn't find resource with id=" + id);
	}
}
