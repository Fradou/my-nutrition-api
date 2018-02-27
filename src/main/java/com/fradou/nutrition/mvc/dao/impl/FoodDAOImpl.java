package com.fradou.nutrition.mvc.dao.impl;

import org.springframework.stereotype.Repository;

import com.fradou.nutrition.mvc.dao.generic.GenericDAOImpl;
import com.fradou.nutrition.mvc.dao.interfaces.FoodDAO;
import com.fradou.nutrition.mvc.entity.Food;

@Repository
public class FoodDAOImpl extends GenericDAOImpl<Food> implements FoodDAO {

	public FoodDAOImpl() {
		setClazz(Food.class);
	}
}
