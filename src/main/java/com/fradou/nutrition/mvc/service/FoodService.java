package com.fradou.nutrition.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.FoodDAOImpl;
import com.fradou.nutrition.mvc.entity.Food;
import com.fradou.nutrition.mvc.service.generic.GenericServiceImpl;

@Service
@Transactional(rollbackFor = Exception.class)
public class FoodService extends GenericServiceImpl<Food, FoodDAOImpl> {
	
}
