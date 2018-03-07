package com.fradou.nutrition.mvc.utils.exception;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;

public class CannotCreateEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CannotCreateEntityException(Class<? extends GenericEntity> clazz, String field) {
		super("Couldn't create " + clazz.getSimpleName() + " entity : incorrect value for " + field + " field.");
	}
}
