package com.fradou.nutrition.mvc.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fradou.nutrition.mvc.dao.generic.GenericDAOImpl;
import com.fradou.nutrition.mvc.entity.work.PantryItem;

@Repository
public class PantryItemDAOImpl extends GenericDAOImpl<PantryItem> {

	public PantryItemDAOImpl() {
		setClazz(PantryItem.class);
	}
	
/**	public List<PantryItem> getByExpiration(LocalDate expirationDate) {
		
		getSession().
	}**/
}
