package com.fradou.nutrition.mvc.service.generic;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;

/**
 * Generic interface for all app's services
 * @author AF
 *
 * @param <T>
 */
@Component
public interface GenericEntityService<T extends GenericEntity> {

	public int create(T entity);
	
	public T find(int id);
		
	public T find(int id, String entityGraph);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public void deleteById(int id);
	
	public List<T> findAll();
	
	public T findUniqueBy(String field, String value);
		
	public boolean alreadyExists(String fieldName, String fieldValue);
	
	public List<T> find(Integer user_id, String orderBy, String sortBy, Integer offset, Integer entries, String entityGraph);
	
	public boolean belongToUser(T entity, int user_id);
	
	public List<T> findAllBy(String fieldName, Object value);

}