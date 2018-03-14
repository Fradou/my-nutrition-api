package com.fradou.nutrition.mvc.dao;

import org.springframework.stereotype.Repository;

import com.fradou.nutrition.mvc.dao.generic.GenericDAOImpl;
import com.fradou.nutrition.mvc.entity.work.Intake;

@Repository
public class IntakeDAOImpl extends GenericDAOImpl<Intake> {

	public IntakeDAOImpl() {
		setClazz(Intake.class);
	}
}
