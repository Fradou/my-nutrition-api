package com.fradou.nutrition.mvc.dao.interfaces;

import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.dao.generic.GenericDAO;
import com.fradou.nutrition.mvc.entity.CustomUser;

@Component
public interface UserDAO extends GenericDAO<CustomUser> {

}
