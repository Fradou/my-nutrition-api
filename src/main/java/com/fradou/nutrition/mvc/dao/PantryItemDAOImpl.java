package com.fradou.nutrition.mvc.dao;

import com.fradou.nutrition.mvc.dao.generic.GenericDAOImpl;
import com.fradou.nutrition.mvc.entity.work.PantryItem;

public class PantryItemDAOImpl extends GenericDAOImpl<PantryItem> {

	public PantryItemDAOImpl() {
		setClazz(PantryItem.class);
	}
}
