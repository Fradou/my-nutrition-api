package com.fradou.nutrition.mvc.dao.impl;

import org.springframework.stereotype.Repository;

import com.fradou.nutrition.mvc.dao.RoleDAO;
import com.fradou.nutrition.mvc.dao.generic.GenericDAOImpl;
import com.fradou.nutrition.mvc.entity.security.Role;

@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role> implements RoleDAO {

	public RoleDAOImpl() {
		setClazz(Role.class);
	}
}
