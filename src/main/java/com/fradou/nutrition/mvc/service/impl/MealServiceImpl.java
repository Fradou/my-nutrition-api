package com.fradou.nutrition.mvc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.impl.MealDAOImpl;
import com.fradou.nutrition.mvc.entity.work.Meal;
import com.fradou.nutrition.mvc.service.MealService;
import com.fradou.nutrition.mvc.service.generic.GenericServiceImpl;

/**
 * Service for Meal (nutrition entity)
 * 
 * @author AF
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MealServiceImpl extends GenericServiceImpl<Meal, MealDAOImpl> implements MealService {

	@Override
	public boolean belongToUser(Meal meal, int user_id) {
		
		int user_meal = meal.getIntake().getUser().getId();
		if(user_meal == user_id) {
			return true;
		}
		else {
			return false;			
		}
	}
}
