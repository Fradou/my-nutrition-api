package com.fradou.nutrition.mvc.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.utils.work.MealType;

@Entity
@Component
@Table(name="meal")
public class Meal extends GenericEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Enumerated(EnumType.ORDINAL)
	private MealType mealType;
	
	@Override
	protected String initializeEntityPath() {
		return "/meal";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MealType getMealType() {
		return mealType;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}
}
