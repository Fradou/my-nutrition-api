package com.fradou.nutrition.mvc.dao.impl;

import org.springframework.stereotype.Repository;

import com.fradou.nutrition.mvc.dao.UserDAO;
import com.fradou.nutrition.mvc.dao.generic.GenericDAOImpl;
import com.fradou.nutrition.mvc.entity.security.CustomUser;

@Repository
public class UserDAOImpl extends GenericDAOImpl<CustomUser> implements UserDAO {

	public UserDAOImpl() {
		setClazz(CustomUser.class);
	}
}
