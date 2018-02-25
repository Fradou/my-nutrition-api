package com.fradou.nutrition.mvc.entity.generic;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fradou.nutrition.mvc.utils.hateoas.RelationType;

@Entity
public class GenericEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	protected String entityPath;
	protected Map<RelationType,String> links;

	public GenericEntity() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<RelationType, String> getLinks() {
		return links;
	}

	protected void setLinks(Map<RelationType, String> links) {
		
		Map<RelationType,String> linksList = new HashMap<>();
		linksList.put(RelationType.LIST, entityPath);
		linksList.put(RelationType.SELF, entityPath + "/" + String.valueOf(id));
		this.links = links;
	}
	
}
