package com.fradou.nutrition.mvc.utils.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fradou.nutrition.mvc.entity.work.Food;
import com.fradou.nutrition.mvc.utils.serializer.generic.ApiGenericSerializer;

public class FoodSerializer extends ApiGenericSerializer<Food> {

	private static final long serialVersionUID = 1L;

	protected FoodSerializer() {
		super(Food.class);
	}

	@Override
	public void serialize(Food value, JsonGenerator gen, SerializerProvider provider) throws IOException {

		gen.writeStartObject();
		
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("name", value.getName());
		gen.writeNumberField("protein", value.getProtein());
		gen.writeNumberField("carbohydrate", value.getCarbohydrate());
		gen.writeNumberField("fat", value.getFat());
		gen.writeNumberField("calorie", value.getCalorie());
		
		gen.writeEndObject();
	}

}
