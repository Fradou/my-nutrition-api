package com.fradou.nutritiontest;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.config.HibernateConfig;
import com.fradou.nutrition.mvc.dao.IntakeDAOImpl;
import com.fradou.nutrition.mvc.entity.work.Intake;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
  classes = { HibernateConfig.class },
  loader = AnnotationConfigContextLoader.class)
@Transactional
public class IntakeTest {
	
	@Test
	public void test() {
		
	}
	
//	@Test
	public void testPostAndGetIntake() {
		
		IntakeDAOImpl iDAO = new IntakeDAOImpl();
		
		Intake intake = new Intake();
		intake.setIntakeDate(LocalDate.now());
		
		iDAO.create(intake);
		System.out.println("Id obtenu : " + intake.getId());
		
		Intake intake2 = iDAO.find(intake.getId());
		System.out.println("Intake du : " + intake2.getIntakeDate());
	}
}
