package com.fradou.nutrition.mvc.dao;

import java.time.LocalDate;
import java.util.List;

import com.fradou.nutrition.mvc.dao.generic.GenericDAO;
import com.fradou.nutrition.mvc.entity.work.PantryItem;

public interface PantryItemDAO extends GenericDAO<PantryItem> {

	public List<PantryItem> getByExpiration(LocalDate expirationDate, Integer userId);
}
