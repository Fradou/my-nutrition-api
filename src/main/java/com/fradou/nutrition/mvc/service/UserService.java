package com.fradou.nutrition.mvc.service;

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

	public boolean usernameExists(String username) {
		return uDao.findUniqueBy("username", username) != null;
	}
}
