package com.fradou.nutrition.mvc.entity.work;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.utils.deserializer.EntityIdResolver;

@Entity
@Table(name="meal_detail")
@NamedEntityGraph(
		name="graph.MealDetailFood",
		attributeNodes = @NamedAttributeNode(value="food")
)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        resolver = EntityIdResolver.class,
        scope=MealDetail.class
)
public class MealDetail extends GenericEntity {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="meal_id")
	private Meal meal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="food_id")
	private Food food;
	
	@Min(0)
	@NotNull
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
	
	@Override
	public boolean isUserRelated() {
		return true;
	}
}
