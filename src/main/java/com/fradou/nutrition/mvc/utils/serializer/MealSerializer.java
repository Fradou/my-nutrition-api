package com.fradou.nutrition.mvc.utils.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fradou.nutrition.mvc.entity.work.Meal;
import com.fradou.nutrition.mvc.entity.work.MealDetail;
import com.fradou.nutrition.mvc.utils.serializer.generic.ApiGenericSerializer;

/**
 * Custom jackson serializer for meal entity
 * 
 * @author AF
 *
 */
public class MealSerializer extends ApiGenericSerializer<Meal> {

	private static final long serialVersionUID = 1L;

	protected MealSerializer() {
		super(Meal.class);
	}

	@Override
	public void serialize(Meal value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("mealType", value.getMealType().toString());
		gen.writeArrayFieldStart("mealDetails");
		for(MealDetail mealDetail : value.getMealDetails()) {
			gen.writeStartObject();
			gen.writeNumberField("id", mealDetail.getId());
			setLinks(mealDetail, gen);
			gen.writeEndObject();
		}
		gen.writeEndArray();
		gen.writeEndObject();	
	}
}
