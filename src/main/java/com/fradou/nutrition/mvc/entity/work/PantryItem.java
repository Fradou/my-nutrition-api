package com.fradou.nutrition.mvc.entity.work;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.entity.security.CustomUser;
import com.fradou.nutrition.mvc.utils.serializer.PantryItemSerializer;

@Entity
@Table(name="pantry_item")
@JsonSerialize(using = PantryItemSerializer.class)
@NamedEntityGraph(
		name="graph.PantryItemFood",
		attributeNodes = @NamedAttributeNode(value="food")
)
public class PantryItem extends GenericEntity {		
	
	@Min(0)
	private Integer weight;
	
	@Min(0)
	private Integer share;
	
	private LocalDate expirationDate;
	
	private LocalDate purchaseDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private CustomUser user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="food_id")
	private Food food;
	
	@Override
	protected String initializeEntityPath() {
		return "/api/pantryItem";
	}

	public CustomUser getUser() {
		return user;
	}

	public void setUser(CustomUser user) {
		this.user = user;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Integer getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
	
	@Override
	public boolean isUserRelated() {
		return true;
	}
}
