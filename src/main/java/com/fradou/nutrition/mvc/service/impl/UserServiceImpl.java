package com.fradou.nutrition.mvc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.impl.UserDAOImpl;
import com.fradou.nutrition.mvc.entity.security.CustomUser;
import com.fradou.nutrition.mvc.service.UserService;
import com.fradou.nutrition.mvc.service.generic.GenericEntityServiceImpl;

/**
 * Service for CustomUser (Security)
 * 
 * @author AF
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends GenericEntityServiceImpl<CustomUser, UserDAOImpl> implements UserService {

	@Override
	public boolean belongToUser(CustomUser user, int user_id) {
		
		if(user.getId() == user_id) {
			return true;
		};
		
		return false;
	}
}
