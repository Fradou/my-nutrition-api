package com.fradou.nutrition.mvc.service.generic;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Generic interface for all app's services
 * @author AF
 *
 * @param <T>
 */
@Component
public interface GenericService<T> {

	public int create(T entity);
	
	public T find(int id);
	
	public T find(int id, int user_id);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public void deleteById(int id);
	
	public List<T> findAll();
	
	public List<T> findAll(int user_id);
	
	public T findUniqueBy(String field, String value);
		
	public boolean alreadyExists(String fieldName, String fieldValue);
	
	public List<T> find(int user_id, String orderBy, String sortBy, Integer offset, Integer entries, String entityGraph);

}