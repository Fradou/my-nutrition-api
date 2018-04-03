package com.fradou.nutrition.mvc.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fradou.nutrition.mvc.dao.generic.GenericDAOImpl;
import com.fradou.nutrition.mvc.entity.work.PantryItem;

@Repository
public class PantryItemDAOImpl extends GenericDAOImpl<PantryItem> {

	public PantryItemDAOImpl() {
		setClazz(PantryItem.class);
	}
	
	public List<PantryItem> getByExpiration(LocalDate expirationDate, Integer userId){
		
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		
		CriteriaQuery<PantryItem> q = cb.createQuery(PantryItem.class);
		Root<PantryItem> root = q.from(PantryItem.class);
		q.select(root);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(root.get("expirationDate"), expirationDate));
		if(userId != null && userId > 0) {
			predicates.add(cb.equal(root.get("id"), userId));
		}
		q.where(cb.and(predicates.toArray(new Predicate[] {})));
		
		TypedQuery<PantryItem> query = getSession().createQuery(q);
		
		return query.getResultList();
	}
}
