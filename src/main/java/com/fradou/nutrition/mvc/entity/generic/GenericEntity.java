package com.fradou.nutrition.mvc.entity.generic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fradou.nutrition.mvc.utils.hateoas.RelationType;

/**
 * Generic abstract entity that will be extended by all work/nutrition entities.
 * 
 * @author AF
 */
@Component
@JsonIgnoreProperties(value = { "entityPath" })
@MappedSuperclass
public abstract class GenericEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	protected int id;
	
	@Transient
	protected String entityPath;
	
	public static boolean IS_USER_RELATED;

	public GenericEntity() {
		entityPath = initializeEntityPath();
		IS_USER_RELATED = isUserRelated();
	}
	
	public abstract boolean isUserRelated();
	
	/**
	 * Abstract method to force subclass to declare entityPath
	 * @return
	 */
	protected abstract String initializeEntityPath();
	
	public String getLinks(RelationType linkType) {
		String url = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + entityPath;

		switch(linkType) {
			case LIST:
				return url;
			case SELF:
				return url + "/" + id;
			default:
				throw new RuntimeException("Unknown linkType");
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
