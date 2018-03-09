package com.fradou.nutrition.mvc.entity.generic;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fradou.nutrition.mvc.utils.hateoas.RelationType;

@Component
@JsonIgnoreProperties(value = { "entityPath" })
@MappedSuperclass
public abstract class GenericEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	protected int id;
	
	@Transient
	protected String entityPath;
	
	@Transient
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
