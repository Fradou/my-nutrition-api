package com.fradou.nutrition.mvc.dao.impl;

import org.springframework.stereotype.Repository;

import com.fradou.nutrition.mvc.dao.IntakeDAO;
import com.fradou.nutrition.mvc.dao.generic.GenericDAOImpl;
import com.fradou.nutrition.mvc.entity.work.Intake;

@Repository
public class IntakeDAOImpl extends GenericDAOImpl<Intake> implements IntakeDAO {

	public IntakeDAOImpl() {
		setClazz(Intake.class);
	}
}
