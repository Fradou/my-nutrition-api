package com.fradou.nutrition.mvc.dao.generic;

import java.util.List;

public interface GenericDAO<T> {
	
	public int create(T entity);
	
	public T find(int id);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public List<T> findAll();
	
	public int count();
	
}
