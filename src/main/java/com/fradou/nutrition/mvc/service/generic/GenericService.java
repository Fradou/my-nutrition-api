package com.fradou.nutrition.mvc.service.generic;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface GenericService<T> {

	public int create(T entity);
	
	public T find(int id);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public List<T> findAll();
	
	public T findUniqueBy(String field, String value);
		
	public boolean alreadyExists(String fieldName, String fieldValue);
	
	public List<T> find(Integer offset, Integer entries);

}