package com.fradou.nutrition.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.interfaces.UserDAO;
import com.fradou.nutrition.mvc.entity.CustomUser;

@Service
public class UserService {

	@Autowired
	private UserDAO uDao;
	
	@Transactional
	public CustomUser findBy(String field, String value) {
		return uDao.findBy(field, value);
	}
	
	@Transactional
	public void update(CustomUser user) {
		uDao.update(user);
	}
}
