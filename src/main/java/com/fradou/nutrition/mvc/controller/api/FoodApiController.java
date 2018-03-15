package com.fradou.nutrition.mvc.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.nutrition.mvc.entity.work.Food;

/**
 * Controller in charge of Food calls (nutrition entity)
 * 
 * @author AF
 */
@RestController
@RequestMapping("/api/food")
public class FoodApiController extends GenericApiController<Food> {
	
	protected String setDefaultEntityGraph() {
		return null;
	}
	
	@Override
	protected boolean setUserDependant() {
		return false;
	}
	
	/**
	 * For now, disable Food deletion.
	 * @param id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public void delete(@PathVariable("id") int id, Authentication authenticate) {
		
		
	}
}
