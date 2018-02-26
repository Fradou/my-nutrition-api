package com.fradou.nutrition.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.nutrition.mvc.entity.Food;
import com.fradou.nutrition.mvc.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodApiController {
	
	@Autowired
	private FoodService fs;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public String findAll() {
		return "Hello bro 2";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Food findById(@PathVariable(value="id") String id) {
		Integer myId = Integer.valueOf(id);
		return fs.get(myId);
	}
	
}
