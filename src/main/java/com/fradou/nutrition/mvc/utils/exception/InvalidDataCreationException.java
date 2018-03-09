package com.fradou.nutrition.mvc.utils.exception;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;

/**
 * Custom exception for all Rest call with incorrect data for entity creation.
 * 
 * @author AF
 *
 */
public class InvalidDataCreationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidDataCreationException(Class<? extends GenericEntity> clazz, String field) {
		super("Couldn't create " + clazz.getSimpleName() + " entity : incorrect value for " + field + " field.");
	}
}
