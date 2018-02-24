package com.fradou.nutrition.mvc.dao.generic;

import java.util.List;

public interface GenericDAO<T> {
	
	public T saveOrUpdate(T entity);
	public T findById(int id);
	public List<T> findAll();
	public int count();
	
}
