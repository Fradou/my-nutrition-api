package com.fradou.nutrition.mvc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.impl.IntakeDAOImpl;
import com.fradou.nutrition.mvc.entity.work.Intake;
import com.fradou.nutrition.mvc.service.IntakeService;
import com.fradou.nutrition.mvc.service.generic.GenericEntityServiceImpl;

/**
 * Service for Intake (nutrition entity)
 * 
 * @author AF
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IntakeServiceImpl extends GenericEntityServiceImpl<Intake, IntakeDAOImpl> implements IntakeService {

	@Override
	public boolean belongToUser(Intake intake, int user_id) {
		
		if(intake.getUser().getId() == user_id) {
			return true;
		}
		else {
			return false;
		}
	}
}
