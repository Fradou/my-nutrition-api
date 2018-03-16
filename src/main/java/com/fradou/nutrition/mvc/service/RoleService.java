package com.fradou.nutrition.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.RoleDAOImpl;
import com.fradou.nutrition.mvc.entity.security.Role;
import com.fradou.nutrition.mvc.service.generic.GenericServiceImpl;

/**
 * Service for Role (Security)
 * 
 * @author AF
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleService extends GenericServiceImpl<Role, RoleDAOImpl> {

	@Override
	public boolean belongToUser(Role entity, int user_id) {
		return false;
	}
}
