package com.fradou.nutrition.mvc.entity.work;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.utils.serializer.FoodSerializer;

/**
 * Entity for food
 * 
 * @author AF
 */
@Entity
@Component
@Table(name="food")
@JsonSerialize(using = FoodSerializer.class)
public class Food extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false)
	@NotNull
	private String name;
	
	@Min(0)
	@Max(100)
	@NotNull
	private double protein;
	
	@Min(0)
	@Max(100)
	@NotNull
	private double carbohydrate;
	
	@Min(0)
	@Max(100)
	@NotNull
	private double fat;
	
	@Min(0)
	@Max(1000)
	@NotNull
	private double calorie;
	
	@OneToMany(mappedBy="food")
	private Set<MealDetail> mealDetails = new HashSet<MealDetail>();
		
	@Override
	protected String initializeEntityPath() {
		return "/api/food";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public double getCalorie() {
		return calorie;
	}

	public void setCalorie(double calorie) {
		this.calorie = calorie;
	}

	@Override
	public boolean isUserRelated() {
		return false;
	}
}
