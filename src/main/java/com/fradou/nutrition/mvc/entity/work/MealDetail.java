package com.fradou.nutrition.mvc.entity.work;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;

@Entity
@Table(name="meal_detail")
public class MealDetail extends GenericEntity {

	@ManyToOne
	@JoinColumn(name="meal_id")
	private Meal meal;
	
	@ManyToOne
	@JoinColumn(name="food_id")
	private Food food;
	
	@Min(0)
	private int quantity;
	
	public Meal getMeal() {
		return meal;
	}
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	protected String initializeEntityPath() {
		return "/mealDetail";
	}
}
