package com.fradou.nutrition.mvc.dao;

import org.springframework.stereotype.Repository;

import com.fradou.nutrition.mvc.dao.generic.GenericDAOImpl;
import com.fradou.nutrition.mvc.entity.work.Meal;

@Repository
public class MealDAOImpl extends GenericDAOImpl<Meal> {

	public MealDAOImpl() {
		setClazz(Meal.class);
	}
}
