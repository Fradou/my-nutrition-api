package com.fradou.nutrition.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fradou.nutrition.mvc.dao.FoodDAO;
import com.fradou.nutrition.mvc.entity.Food;

@Service
public class FoodService {

	@Autowired
	private FoodDAO fDao;
	
	public Food get(int id) {
		return fDao.find(id);
	}
}
