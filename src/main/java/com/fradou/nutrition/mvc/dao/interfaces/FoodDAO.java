package com.fradou.nutrition.mvc.dao.interfaces;

import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.dao.generic.GenericDAO;
import com.fradou.nutrition.mvc.entity.Food;

@Component
public interface FoodDAO extends GenericDAO <Food> {

}
