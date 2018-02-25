package com.fradou.nutrition.mvc.dao.generic;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDAOImpl<T> implements GenericDAO<T> {

	private final Class<T> type;
	
	@Autowired
	protected SessionFactory sf;
	
	public GenericDAOImpl(Class<T> type) {
		this.type = type;
	}
	
	@Override
	public int create(T o) {
		return (int) getSession().save(o);
	}

	@Override
	public T find(int id) {
		return getSession().get(type, id);
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
		Query<T> query = getSession().createQuery("FROM " + type.getName());
		return query.getResultList();
	}

	@Override
	public int count() {
		Query<T> query = getSession().createQuery("SELECT COUNT(*) FROM " + type.getName());
		return (int) query.getSingleResult();
	}
	
	protected Session getSession() {
		return sf.getCurrentSession();
	}
}
