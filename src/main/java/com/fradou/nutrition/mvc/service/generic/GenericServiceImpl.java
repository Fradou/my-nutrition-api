package com.fradou.nutrition.mvc.service.generic;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.dao.generic.GenericDAOImpl;
import com.fradou.nutrition.mvc.utils.exception.ResourceNotFoundException;

@Component
@Transactional
public abstract class GenericServiceImpl<T, D extends GenericDAOImpl<T>> implements GenericService<T> {

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
				System.out.println("Error : " + exception.getMessage());
			}
		}
		return exists;
	}
	
	public List<T> find(Integer offset, Integer entries, String orderBy) {
		return dao.find(offset, entries);
	}

	@Override
	public T find(int id, int user_id) {
		return dao.find(id, user_id);
	}

	@Override
	public List<T> findAll(int user_id) {
		return dao.findAll(user_id);
	}
}

