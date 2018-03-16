package com.fradou.nutrition.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.UserDAOImpl;
import com.fradou.nutrition.mvc.entity.security.CustomUser;
import com.fradou.nutrition.mvc.service.generic.GenericServiceImpl;

/**
 * Service for CustomUser (Security)
 * 
 * @author AF
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends GenericServiceImpl<CustomUser, UserDAOImpl> {

	@Override
	public boolean belongToUser(CustomUser user, int user_id) {
		
		if(user.getId() == user_id) {
			return true;
		};
		
		return false;
	}
}
