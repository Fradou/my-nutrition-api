package com.fradou.nutrition.mvc.entity.work;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.entity.security.CustomUser;
import com.fradou.nutrition.mvc.utils.deserializer.EntityIdResolver;
import com.fradou.nutrition.mvc.utils.serializer.PantryItemSerializer;
import com.fradou.nutrition.mvc.utils.validator.ShareOrWeight;

import lombok.Getter;
import lombok.Setter;

/**
 * Nutrition entity that will be used for PantryManagement
 * @author AF
 *
 */
@Entity
@Table(name="pantry_item")
@ShareOrWeight
@Setter @Getter
@JsonSerialize(using = PantryItemSerializer.class)
@NamedEntityGraph(
		name="graph.PantryItemFood",
		attributeNodes = @NamedAttributeNode(value="food")
)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        resolver = EntityIdResolver.class,
        scope=PantryItem.class
)
public class PantryItem extends GenericEntity {		
	
	private static final long serialVersionUID = 1L;

	@Min(0)
	private Integer weight;
	
	@Min(0)
	private Integer share;
	
	@FutureOrPresent
	private LocalDate expirationDate;
	
	private LocalDate purchaseDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private CustomUser user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="food_id")
	@NotNull
	private Food food;
	
	@Override
	protected String initializeEntityPath() {
		return "/api/pantryItem";
	}
	
	@Override
	public boolean isUserRelated() {
		return true;
	}
}
