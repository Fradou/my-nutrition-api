package com.fradou.nutrition.mvc.dao.generic;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Generic abstract DAO that will be extended by all DAO
 * 
 * @author AF
 * @param <T>
 */
@Repository
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

	private Class<T> clazz;
	
	@Autowired
	protected SessionFactory sf;
	
	public final void setClazz( Class< T > clazzToSet ){
		this.clazz = clazzToSet;
	}
	
	@Override
	public int create(T o) {
		return (int) getSession().save(o);
	}

	@Override
	public T find(int id) {
		return getSession().get(clazz, id);
	}
	
	@Override
	public T find(int id,int user_id) {
				
		Query<T> query = getSession().createQuery("FROM " + clazz.getName() + " WHERE id=:entityId AND user_id=:userId");
		query.setParameter("entityId", id);
		query.setParameter("userId", user_id);
		
		return query.getSingleResult();
	}

	@Override
	public void update(T o) {
		getSession().update(o);
	}
	
	@Override
	public void delete(T o) {
		getSession().delete(o);
	}

	@Override
	public List<T> findAll() {
		Query<T> query = getSession().createQuery("FROM " + clazz.getName());
		return query.getResultList();
	}
	
	@Override
	public List<T> findAll(int user_id) {
		Query<T> query = getSession().createQuery("FROM " + clazz.getName() + " WHERE user_id=:userId");
		query.setParameter("userId", user_id);
		
		return query.getResultList();
	}

	@Override
	public int count() {
		Query<T> query = getSession().createQuery("SELECT COUNT(*) FROM " + clazz.getName());
		return (int) query.getSingleResult();
	}

	@Override
	public T findUniqueBy(String field, String value) {
		Query<T> query = getSession().createQuery("FROM " + clazz.getName() + " WHERE " + field + "='" + value + "'");
		return query.getSingleResult();
	}

	@Override
	public List<T> find(Integer offset, Integer entries) {
		
		Query<T> query = getSession().createQuery("FROM " + clazz.getName());
		if(offset != null) {
			query.setFirstResult(offset);
		}
		if(entries != null) {
			query.setMaxResults(entries);
		}
		
		return query.getResultList();
	}
	
	protected Session getSession() {
		return sf.getCurrentSession();
	}
}
