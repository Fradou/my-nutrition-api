package com.fradou.nutrition.mvc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.impl.RoleDAOImpl;
import com.fradou.nutrition.mvc.entity.security.Role;
import com.fradou.nutrition.mvc.service.RoleService;
import com.fradou.nutrition.mvc.service.generic.GenericServiceImpl;

/**
 * Service for Role (Security)
 * 
 * @author AF
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends GenericServiceImpl<Role, RoleDAOImpl> implements RoleService {

	@Override
	public boolean belongToUser(Role entity, int user_id) {
		return false;
	}
}
