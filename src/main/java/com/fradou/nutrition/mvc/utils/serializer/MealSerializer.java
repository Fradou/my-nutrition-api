package com.fradou.nutrition.mvc.utils.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fradou.nutrition.mvc.entity.work.Meal;
import com.fradou.nutrition.mvc.entity.work.MealDetail;
import com.fradou.nutrition.mvc.utils.hateoas.RelationType;

/**
 * Custom jackson serializer for meal entity
 * @author AF
 *
 */
public class MealSerializer extends StdSerializer<Meal> {

	private static final long serialVersionUID = 1L;

	protected MealSerializer() {
		super(Meal.class);
	}

	@Override
	public void serialize(Meal value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("mealType", value.getMealType().toString());
		gen.writeArrayFieldStart("mealFoods");
		for(MealDetail meal : value.getMealDetails()) {
			gen.writeStartObject();
			gen.writeNumberField("id", meal.getId());
			gen.writeArrayFieldStart("links");
				gen.writeStartObject();
					gen.writeStringField("rel", RelationType.LIST.toString());
					gen.writeStringField("href", meal.getLinks(RelationType.LIST));
				gen.writeEndObject();
				gen.writeStartObject();
					gen.writeStringField("rel", RelationType.SELF.toString());
					gen.writeStringField("href", meal.getLinks(RelationType.SELF));
				gen.writeEndObject();
			gen.writeEndArray();
			gen.writeEndObject();
		}
		gen.writeEndArray();
		gen.writeEndObject();	
	}
}
