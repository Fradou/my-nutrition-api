package com.fradou.nutrition.mvc.service.generic;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.dao.generic.GenericDAO;
import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.utils.exception.ResourceNotFoundException;

/**
 * Generic implementation for all app's services
 * 
 * @author AF
 *
 * @param <T>
 *            Entity managed by service
 * @param <D>
 *            Entity's DAO
 */
@Component
@Transactional
public abstract class GenericEntityServiceImpl<T extends GenericEntity, D extends GenericDAO<T>> implements GenericEntityService<T> {

	@Autowired
	protected D dao;
	
	public int create(T entity) {
		return dao.create(entity);
	}
	
	public T find(int id) {
		return dao.find(id);
	}
	
	public void update(T entity) {
		dao.update(entity);
	}
	
	public void delete(T entity) {
		dao.delete(entity);
	}
	
	public void deleteById(int id) {
		T entity = dao.find(id);
		if(entity == null) {
			throw new ResourceNotFoundException(id);
		}
		dao.delete(entity);
	}
	
	public List<T> findAll() {
		return dao.findAll();
	}
	
	public T findUniqueBy(String field, String value) {
		return dao.findUniqueBy(field, value);
	}
	
	
	public boolean alreadyExists(String fieldName, String fieldValue) {
		
		boolean exists = false;
		
		if(!fieldValue.isEmpty()) {
			try {
				exists = findUniqueBy(fieldName, fieldValue) != null;
			}
			catch(NoResultException exception) {
				//TODO a reprendre
				System.out.println("Error : " + exception.getMessage());
			}
		}
		return exists;
	}
	
	public List<T> find(Integer user_id, String orderBy, String sortBy, Integer offset, Integer entries, String entityGraph) {
		return dao.find(user_id, orderBy, sortBy, offset, entries, entityGraph);
	}
	
	@Override
	public T find(int id, String entityGraph) {
		return dao.find(id, entityGraph);
	}
	
	public List<T> findAllBy(String fieldName, Object value){
		return dao.findAllBy(fieldName, value);
	}
}

