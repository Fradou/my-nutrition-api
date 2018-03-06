package com.fradou.nutrition.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.UserDAOImpl;
import com.fradou.nutrition.mvc.entity.CustomUser;
import com.fradou.nutrition.mvc.service.generic.GenericServiceImpl;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends GenericServiceImpl<CustomUser, UserDAOImpl> {
	
}
