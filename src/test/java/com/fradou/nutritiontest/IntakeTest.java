package com.fradou.nutritiontest;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.config.HibernateConfig;
import com.fradou.nutrition.mvc.dao.IntakeDAOImpl;
import com.fradou.nutrition.mvc.entity.work.Intake;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
  classes = { HibernateConfig.class },
  loader = AnnotationConfigContextLoader.class)
@Transactional
public class IntakeTest {

	@Resource
	private IntakeDAOImpl iDAO;
	
	@Test
	public void testPostAndGetIntake() {
		
		Intake intake = new Intake();
		intake.setIntakeDate(LocalDate.now());
		
		iDAO.create(intake);
		System.out.println("Id obtenu : " + intake.getId());
		
		Intake intake2 = iDAO.find(intake.getId());
		System.out.println("Intake du : " + intake2.getIntakeDate());
	}
}
