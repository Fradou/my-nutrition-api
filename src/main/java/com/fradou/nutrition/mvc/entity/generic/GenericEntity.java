package com.fradou.nutrition.mvc.entity.generic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fradou.nutrition.mvc.utils.hateoas.RelationType;

@Component
@JsonIgnoreProperties(value = { "entityPath" })
public class GenericEntity {
	
	
	protected String entityPath;
	
	protected Map<RelationType,String> links;

	public GenericEntity() {
		setLinks();
	}

	public Map<RelationType, String> getLinks() {
		return links;
	}

	protected void setLinks() {
		
		Map<RelationType,String> linksList = new HashMap<>();
		linksList.put(RelationType.LIST, entityPath);
		linksList.put(RelationType.SELF, entityPath + "/");
		this.links = linksList;
	}
	
	protected void setEntityPath(String path) {
		this.entityPath = path;
	}
}
