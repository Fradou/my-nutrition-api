package com.fradou.nutrition.mvc.service;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.interfaces.UserDAO;
import com.fradou.nutrition.mvc.entity.CustomUser;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

	@Autowired
	private UserDAO uDao;
	
	public CustomUser findUniqueBy(String field, String value) {
		return uDao.findUniqueBy(field, value);
	}
	
	public void update(CustomUser user) {
		uDao.update(user);
	}

	public boolean alreadyExists(String fieldName, String fieldValue) {
		
		boolean exists = false;
		
		if(!fieldValue.isEmpty()) {
			try {
				exists = uDao.findUniqueBy(fieldName, fieldValue) != null;
			}
			catch(NoResultException exception) {
				System.out.println("Error : " + exception.getMessage());
			}
		}
		return exists;
	}

	public void create(@Valid CustomUser user) {
		uDao.create(user);
	}
}
