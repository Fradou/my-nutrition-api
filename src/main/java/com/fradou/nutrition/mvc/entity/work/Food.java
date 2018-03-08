package com.fradou.nutrition.mvc.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;

@Entity
@Component
@Table(name="food")
public class Food extends GenericEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column(nullable=false)
	@NotNull
	private String name;
	
	@Column
	@Min(0)
	@Max(100)
	private Double protein;
	
	@Column
	@Min(0)
	@Max(100)
	private Double carbohydrate;
	
	@Column
	@Min(0)
	@Max(100)
	private Double fat;
	
	@Column
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
