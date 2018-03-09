package com.fradou.nutrition.mvc.utils.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(int id) {
		super("Couldn't find resource with id=" + id);
	}
}
