package com.fradou.nutrition.mvc.utils.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fradou.nutrition.mvc.entity.work.Food;
import com.fradou.nutrition.mvc.entity.work.PantryItem;
import com.fradou.nutrition.mvc.utils.serializer.generic.ApiGenericSerializer;

/**
 * Custom jackson serializer for PantryItem entity
 * 
 * @author AF
 *
 */
public class PantryItemSerializer extends ApiGenericSerializer<PantryItem> {

	private static final long serialVersionUID = 1L;

	protected PantryItemSerializer() {
		super(PantryItem.class);
	}

	@Override
	public void serialize(PantryItem value, JsonGenerator gen, SerializerProvider provider) throws IOException {

		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		writeIntegerField(gen, "weight", value.getWeight());
		writeIntegerField(gen, "share", value.getShare());		
		Food food = value.getFood();
		gen.writeFieldName("food");
		gen.writeStartObject();
		gen.writeNumberField("id", food.getId());
		gen.writeStringField("name", food.getName());
		setLinks(food, gen);
		
		gen.writeEndObject();
		gen.writeEndObject();
	}
}
