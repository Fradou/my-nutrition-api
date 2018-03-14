package com.fradou.nutrition.mvc.utils.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fradou.nutrition.mvc.entity.work.PantryItem;

/**
 * Custom jackson serializer for PantryItem entity
 * @author AF
 *
 */
public class PantryItemSerializer extends StdSerializer<PantryItem> {

	private static final long serialVersionUID = 1L;

	protected PantryItemSerializer() {
		super(PantryItem.class);
	}

	@Override
	public void serialize(PantryItem value, JsonGenerator gen, SerializerProvider provider) throws IOException {

		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeNumberField("weight", value.getWeight());
		gen.writeNumberField("share", value.getShare());
		gen.writeEndObject();
	}
}
