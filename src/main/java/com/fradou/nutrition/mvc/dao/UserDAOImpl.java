package com.fradou.nutrition.mvc.dao;

import org.springframework.stereotype.Repository;

import com.fradou.nutrition.mvc.dao.generic.GenericDAOImpl;
import com.fradou.nutrition.mvc.entity.CustomUser;

@Repository
public class UserDAOImpl extends GenericDAOImpl<CustomUser> {

	public UserDAOImpl() {
		setClazz(CustomUser.class);
	}
}
