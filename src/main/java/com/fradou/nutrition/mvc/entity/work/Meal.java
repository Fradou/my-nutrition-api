package com.fradou.nutrition.mvc.entity.work;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.utils.work.MealType;

/**
 * Entity for meals (include food, included in intake)
 * 
 * @author AF
 */
@Entity
@Component
@Table(name="meal")
public class Meal extends GenericEntity {
	
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private MealType mealType;
	
	@ManyToOne
	@JoinColumn(name="intake_id")
	private Intake intake;
	
	
	@Override
	protected String initializeEntityPath() {
		return "/meal";
	}

	public MealType getMealType() {
		return mealType;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}

	public Intake getIntake() {
		return intake;
	}

	public void setIntake(Intake intake) {
		this.intake = intake;
	}
}
