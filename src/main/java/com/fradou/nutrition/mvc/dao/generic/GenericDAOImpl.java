package com.fradou.nutrition.mvc.dao.generic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fradou.nutrition.config.Constant;

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
	
	public final void setClazz( Class<T> clazzToSet ){
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
	public T find(int id, String entityGraph) {
		
		Session sess = getSession();
		EntityGraph<T> graph = (EntityGraph<T>) sess.getEntityGraph(entityGraph);
		
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("javax.persistence.loadgraph", graph);
		
		return sess.find(clazz, id, properties);
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
	public List<T> find(Integer user_id, String orderBy, String sortBy, Integer offset, Integer entries, String entityGraph) {
		
		TypedQuery<T> query = getCriteria(user_id, orderBy, sortBy, offset, entries, entityGraph);
		
		return query.getResultList();
	}
	
	protected TypedQuery<T> getCriteria(Integer user_id, String orderBy, String sortBy, Integer offset, Integer entries, String entityGraph ) {
		
		Session sess = getSession();
		
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(clazz);
		Root<T> c = q.from(clazz);
		q.select(c);
		
		if(user_id != null) {
			q.where(cb.equal(c.get("user"), user_id));
		}
		
		if(orderBy != null && !orderBy.isEmpty()) {
			if("desc".equalsIgnoreCase(sortBy)) {
				q.orderBy(cb.desc(c.get(orderBy)));
			}
			else {
				q.orderBy(cb.asc(c.get(orderBy)));
			}
		}
		
		TypedQuery<T> typedQuery = sess.createQuery(q);
		
		if(offset == null || offset < 1) {
			offset = Constant.DEFAULT_PAGE;
		}
		typedQuery.setFirstResult(offset-1);
		
		if(entries == null || entries < 1) {
			entries = Constant.DEFAULT_RESULTS;
		}
		typedQuery.setMaxResults(entries);
		
		if(entityGraph != null && ! entityGraph.isEmpty()) {
			EntityGraph<T> graph = (EntityGraph<T>) sess.getEntityGraph(entityGraph);
			typedQuery.setHint("javax.persistence.loadgraph", graph);
		}
		
		return typedQuery;
	}
	
	protected Session getSession() {
		return sf.getCurrentSession();
	}
	
	protected EntityManager getEntityManager() {
		return getSession().getEntityManagerFactory().createEntityManager();
	}
}
