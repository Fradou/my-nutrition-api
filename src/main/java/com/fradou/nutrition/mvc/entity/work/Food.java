package com.fradou.nutrition.mvc.entity.work;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.utils.deserializer.EntityIdResolver;
import com.fradou.nutrition.mvc.utils.serializer.FoodSerializer;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for food
 * 
 * @author AF
 */
@Entity
@Component
@Setter @Getter
@JsonSerialize(using = FoodSerializer.class)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        resolver = EntityIdResolver.class,
        scope=Food.class
)
public class Food extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false, unique=true)
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

	@Override
	public boolean isUserRelated() {
		return false;
	}
}
