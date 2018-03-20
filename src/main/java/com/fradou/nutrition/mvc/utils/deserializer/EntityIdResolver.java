package com.fradou.nutrition.mvc.utils.deserializer;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdResolver;

@Component
@Transactional
public class EntityIdResolver implements ObjectIdResolver {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public void bindItem(IdKey id, Object pojo) {		
	}

	@Override
	public Object resolveId(IdKey id) {
		return sf.getCurrentSession().find(id.scope, id.key);
	}

	@Override
	public ObjectIdResolver newForDeserialization(Object context) {
		return this;
	}

	@Override
	public boolean canUseFor(ObjectIdResolver resolverType) {
		return false;
	}

}
