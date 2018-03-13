package com.fradou.nutrition.mvc.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.IntakeDAOImpl;
import com.fradou.nutrition.mvc.entity.work.Intake;
import com.fradou.nutrition.mvc.service.generic.GenericServiceImpl;

/**
 * Service for Intake (nutrition entity)
 * 
 * @author AF
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IntakeService extends GenericServiceImpl<Intake, IntakeDAOImpl> {

	public List<Intake> findAll(int user_id) {
		List<Intake> intakes = super.findAll(user_id);
		for(Intake intake : intakes) {
			Hibernate.initialize(intake.getMeals());
		}
		return intakes;
	}
}
