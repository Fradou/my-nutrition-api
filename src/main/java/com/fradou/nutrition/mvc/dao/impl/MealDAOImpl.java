package com.fradou.nutrition.mvc.dao.impl;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.fradou.nutrition.config.Constant;
import com.fradou.nutrition.mvc.dao.MealDAO;
import com.fradou.nutrition.mvc.dao.generic.GenericDAOImpl;
import com.fradou.nutrition.mvc.entity.work.Meal;

@Repository
public class MealDAOImpl extends GenericDAOImpl<Meal> implements MealDAO {

	public MealDAOImpl() {
		setClazz(Meal.class);
	}
	
	protected TypedQuery<Meal> getCriteria(Integer user_id, String orderBy, String sortBy, Integer offset, Integer entries, String entityGraph ) {
		
		Session sess = getSession();
		
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<Meal> q = cb.createQuery(Meal.class);
		Root<Meal> c = q.from(Meal.class);
		q.select(c);
		
		if(user_id != null) {
			q.where(cb.equal(c.get("intake").get("user"), user_id));
		}
		
		if(orderBy != null && !orderBy.isEmpty()) {
			if("desc".equalsIgnoreCase(sortBy)) {
				q.orderBy(cb.desc(c.get(orderBy)));
			}
			else {
				q.orderBy(cb.asc(c.get(orderBy)));
			}
		}
		
		TypedQuery<Meal> typedQuery = sess.createQuery(q);
		
		if(offset == null || offset < 1) {
			offset = Constant.DEFAULT_PAGE;
		}
		typedQuery.setFirstResult(offset-1);
		
		if(entries == null || entries < 1) {
			entries = Constant.DEFAULT_RESULTS;
		}
		typedQuery.setMaxResults(entries);
		
		if(entityGraph != null && ! entityGraph.isEmpty()) {
			EntityGraph<Meal> graph = (EntityGraph<Meal>) sess.getEntityGraph(entityGraph);
			typedQuery.setHint("javax.persistence.loadgraph", graph);
		}
		
		return typedQuery;
	}
}
