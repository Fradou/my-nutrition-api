package com.fradou.nutrition.mvc.dao.generic;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;

/**
 * Generic DAO interface that will be implemented by all DAO
 * 
 * @author AF
 * @param <T>
 */
@Component
public interface GenericDAO<T extends GenericEntity> {
	
	public int create(T entity);
	
	public T find(int id);
	
	public T find(int id, String entityGraph);
		
	public void update(T entity);
		
	public void delete(T entity);
		
	public List<T> findAll(String entityGraph);
	
	public List<T> findAll();
		
	public int count();
	
	public T findUniqueBy(String field, String value);
		
	public List<T> find(Integer user_id, String orderBy, String sortBy, Integer offset, Integer entries,String entityGraph);
	
	public List<T> findAllBy(String fieldName, Object value);
}
