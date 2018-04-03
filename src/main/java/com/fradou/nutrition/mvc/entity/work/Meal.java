package com.fradou.nutrition.mvc.entity.work;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.utils.deserializer.EntityIdResolver;
import com.fradou.nutrition.mvc.utils.serializer.MealSerializer;
import com.fradou.nutrition.mvc.utils.work.MealType;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for meals (include food, included in intake)
 * 
 * @author AF
 */
@Entity
@Component
@Setter @Getter
@JsonSerialize(using = MealSerializer.class)
@NamedEntityGraph(
		name="graph.MealMealDetail",
		attributeNodes = @NamedAttributeNode(value="mealDetails")
)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        resolver = EntityIdResolver.class,
        scope=Meal.class
)
public class Meal extends GenericEntity {

	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private MealType mealType;
	
	@ManyToOne
	@JoinColumn(name="intake_id")
	private Intake intake;
	
	@OneToMany(mappedBy="meal")
	private Set<MealDetail> mealDetails = new HashSet<MealDetail>();
	
	@Override
	protected String initializeEntityPath() {
		return "/api/meal";
	}

	@Override
	public boolean isUserRelated() {
		return true;
	}
}
