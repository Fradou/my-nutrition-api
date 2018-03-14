package com.fradou.nutrition.mvc.dao.generic;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Generic DAO interface that will be implemented by all DAO
 * 
 * @author AF
 * @param <T>
 */
@Component
public interface GenericDAO<T> {
	
	public int create(T entity);
	
	public T find(int id);
	
	public T find(int id, int user_id);
	
	public void update(T entity);
		
	public void delete(T entity);
		
	public List<T> findAll();
	
	public List<T> findAll(int user_id);
	
	public int count();
	
	public T findUniqueBy(String field, String value);
		
	public List<T> find(int user_id, String orderBy, String sortBy, Integer offset, Integer entries,String entityGraph);
	
}
