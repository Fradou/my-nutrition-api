package com.fradou.nutrition.mvc.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.impl.PantryItemDAOImpl;
import com.fradou.nutrition.mvc.entity.work.PantryItem;
import com.fradou.nutrition.mvc.service.PantryItemService;
import com.fradou.nutrition.mvc.service.generic.GenericServiceImpl;

/**
 * Service for PantryItem (Nutrition)
 * 
 * @author AF
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PantryItemServiceImpl extends GenericServiceImpl<PantryItem, PantryItemDAOImpl> implements PantryItemService {

	@Override
	public boolean belongToUser(PantryItem item, int user_id) {

		if(item.getUser().getId() == user_id) {
			return true;
		}

		return false;
	}

	public List<PantryItem> getNearlyExpiredItem(LocalDate referenceDate, Integer userId){
		return dao.getByExpiration(referenceDate, userId);
	}
}
