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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.entity.security.CustomUser;
import com.fradou.nutrition.mvc.utils.deserializer.EntityIdResolver;
import com.fradou.nutrition.mvc.utils.serializer.IntakeSerializer;
import com.fradou.nutrition.mvc.utils.work.MealType;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for daily intake.
 * 
 * @author AF
 */
@Entity
@Component
@Setter @Getter
@JsonSerialize(using = IntakeSerializer.class)
@NamedEntityGraph(
		name="graph.IntakeMeal",
		attributeNodes = @NamedAttributeNode(value="meals")
)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        resolver = EntityIdResolver.class,
        scope=Intake.class
)
public class Intake extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private LocalDate intakeDate; 
	
	@Min(0)
	@NotNull
	private double protein;
	
	@Min(0)
	@NotNull
	private double carbohydrate;
	
	@Min(0)
	@NotNull
	private double fat;
	
	@Min(0)
	@NotNull
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

	@Override
	public boolean isUserRelated() {
		return true;
	}
}
