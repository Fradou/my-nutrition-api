package com.fradou.nutrition.mvc.dao;

import org.springframework.stereotype.Repository;

import com.fradou.nutrition.mvc.dao.generic.GenericDAOImpl;
import com.fradou.nutrition.mvc.entity.Food;

@Repository
public class FoodDAOImpl extends GenericDAOImpl<Food> implements FoodDAO {

	public FoodDAOImpl() {
		setClazz(Food.class);
	}
}
