package com.fradou.nutrition.mvc.utils.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fradou.nutrition.mvc.entity.work.PantryItem;

public class PantryItemValidator implements ConstraintValidator<ShareOrWeight, PantryItem> {

	@Override
	public boolean isValid(PantryItem item, ConstraintValidatorContext context) {

		return item.getShare() != null || item.getWeight() != null;		
	}

}
