package com.fradou.nutrition.mvc.entity.work;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.entity.security.CustomUser;

@Entity
@Table(name="pantry_item")
public class PantryItem extends GenericEntity {	
	
	@ManyToOne
	@JoinColumn(name="food_id")
	private Food food;	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private CustomUser user;
	
	private LocalDate expirationDate;
	
	private LocalDate purchaseDate;
	
	@Min(0)
	private int weight;
	
	@Min(0)
	private int share;
	
	@Override
	protected String initializeEntityPath() {
		return "/pantryItem";
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}	
}
