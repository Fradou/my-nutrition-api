package com.fradou.nutrition.mvc.dao.generic;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface GenericDAO<T> {
	
	public int create(T entity);
	
	public T find(int id);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public List<T> findAll();
	
	public int count();
	
	public T findBy(String field, String value);
	
	public List<T> find(Integer offset, Integer entries, String orderBy, String orderType);
	
}
