package com.fradou.nutrition.mvc.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;

/**
 * Entity for food
 * 
 * @author AF
 */
@Entity
@Component
@Table(name="food")
public class Food extends GenericEntity {
	
	@Column(nullable=false)
	@NotNull
	private String name;
	
	@Min(0)
	@Max(100)
	private Double protein;
	
	@Min(0)
	@Max(100)
	private Double carbohydrate;
	
	@Min(0)
	@Max(100)
	private Double fat;
	
	@Max(100)
	private Double calorie;
	
	public Food() {
		
	}
	
	public Food(String name) {
		this.name = name;
	}
	
	@Override
	protected String initializeEntityPath() {
		return "/food";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getProtein() {
		return protein;
	}

	public void setProtein(Double protein) {
		this.protein = protein;
	}

	public Double getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(Double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public Double getFat() {
		return fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
	}

	public Double getCalorie() {
		return calorie;
	}

	public void setCalorie(Double calorie) {
		this.calorie = calorie;
	}
}
