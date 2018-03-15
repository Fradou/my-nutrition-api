package com.fradou.nutrition.mvc.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
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
}
