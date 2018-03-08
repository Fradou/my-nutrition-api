package com.fradou.nutrition.mvc.entity.generic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fradou.nutrition.mvc.utils.hateoas.RelationType;

@Component
@JsonIgnoreProperties(value = { "entityPath" })
public abstract class GenericEntity {
	
	protected String entityPath;
	
	protected Map<RelationType,String> links;

	public GenericEntity() {
		entityPath = initializeEntityPath();
		setLinks();
	}
	
	/**
	 * Abstract method to force subclass to declare entityPath
	 * @return
	 */
	protected abstract String initializeEntityPath();

	protected void setLinks() {
		Map<RelationType,String> linksList = new HashMap<>();
		linksList.put(RelationType.LIST, entityPath);
		linksList.put(RelationType.SELF, entityPath + "/");
		this.links = linksList;
	}
	
	public Map<RelationType, String> getLinks() {
		return links;
	}
}
