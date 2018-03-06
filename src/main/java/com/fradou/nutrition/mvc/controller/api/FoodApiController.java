package com.fradou.nutrition.mvc.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.nutrition.mvc.entity.Food;
import com.fradou.nutrition.mvc.service.FoodService;

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
		return fs.find(myId);
	}
	
}
