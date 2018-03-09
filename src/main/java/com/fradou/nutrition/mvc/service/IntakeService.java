package com.fradou.nutrition.mvc.service;

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

}
