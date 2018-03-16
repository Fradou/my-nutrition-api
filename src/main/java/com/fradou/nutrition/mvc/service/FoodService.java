package com.fradou.nutrition.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.FoodDAOImpl;
import com.fradou.nutrition.mvc.entity.work.Food;
import com.fradou.nutrition.mvc.service.generic.GenericServiceImpl;

/**
 * Service for Food (nutrition entity)
 * 
 * @author AF
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FoodService extends GenericServiceImpl<Food, FoodDAOImpl> {

	@Override
	public boolean belongToUser(Food entity, int user_id) {
		return false;
	}
}
