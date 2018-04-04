package com.fradou.nutrition.mvc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.impl.FoodDAOImpl;
import com.fradou.nutrition.mvc.entity.work.Food;
import com.fradou.nutrition.mvc.service.FoodService;
import com.fradou.nutrition.mvc.service.generic.GenericEntityServiceImpl;

/**
 * Service for Food (nutrition entity)
 * 
 * @author AF
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FoodServiceImpl extends GenericEntityServiceImpl<Food, FoodDAOImpl> implements FoodService {

	@Override
	public boolean belongToUser(Food entity, int user_id) {
		return false;
	}
}
