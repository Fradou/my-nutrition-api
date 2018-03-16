package com.fradou.nutrition.mvc.entity.work;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.entity.security.CustomUser;
import com.fradou.nutrition.mvc.utils.serializer.IntakeSerializer;
import com.fradou.nutrition.mvc.utils.work.MealType;

/**
 * Entity for daily intake.
 * 
 * @author AF
 */
@Entity
@Component
@Table(name="intake")
@JsonSerialize(using = IntakeSerializer.class)
@NamedEntityGraph(
		name="graph.IntakeMeal",
		attributeNodes = @NamedAttributeNode(value="meals")
)
public class Intake extends GenericEntity {
	
	@NotNull
	private LocalDate intakeDate; 
	
	@Min(0)
	private double protein;
	
	@Min(0)
	private double carbohydrate;
	
	@Min(0)
	private double fat;
	
	@Min(0)
	private double calorie;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private CustomUser user;
	
	@OneToMany(mappedBy="intake")
	@MapKeyEnumerated(EnumType.ORDINAL)
	private Map<MealType, Meal> meals = new HashMap<>();
	
	@Override
	protected String initializeEntityPath() {
		return "/api/intake";
	}

	public CustomUser getUser() {
		return user;
	}

	public void setUser(CustomUser user) {
		this.user = user;
	}

	public LocalDate getIntakeDate() {
		return intakeDate;
	}

	public void setIntakeDate(LocalDate intakeDate) {
		this.intakeDate = intakeDate;
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

	public Map<MealType, Meal> getMeals() {
		return meals;
	}

	public void setMeals(Map<MealType, Meal> meals) {
		this.meals = meals;
	}

	@Override
	public boolean isUserRelated() {
		return true;
	}
}
