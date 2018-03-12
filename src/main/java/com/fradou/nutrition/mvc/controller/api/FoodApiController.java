package com.fradou.nutrition.mvc.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.nutrition.mvc.entity.work.Food;
import com.fradou.nutrition.mvc.service.FoodService;
import com.fradou.nutrition.mvc.utils.exception.InvalidDataCreationException;

/**
 * Controller in charge of Food calls (nutrition entity)
 * 
 * @author AF
 */
@RestController
@RequestMapping("/api/food")
public class FoodApiController {
	
	@Autowired
	private FoodService fs;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Food> findAll(
			@RequestParam(value="page", defaultValue="1", required=false) int page,
			@RequestParam(value="delta", defaultValue="20", required=false) int delta,
			@RequestParam(value="name", required=false) String name		
			) {
		
		Integer offest = (page-1) * delta;
		
		return fs.find(offest, delta);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Food findById(@PathVariable(value="id") String id) {
		Integer myId = Integer.valueOf(id);
		Food food = new Food();
		food = fs.find(myId);

		return food;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Integer createFood(@Valid @RequestBody Food newFood,BindingResult validationResult) throws Exception {
		
		if(validationResult.hasErrors()) {
			throw new InvalidDataCreationException(Food.class, validationResult.getFieldError().getField());
		}
		else {
			int id = fs.create(newFood);
			return id;
		}
	}
}
