package com.fradou.nutrition.mvc.utils.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fradou.nutrition.mvc.entity.security.CustomUser;
import com.fradou.nutrition.mvc.utils.serializer.generic.ApiGenericSerializer;

/**
 * Custom jackson serializer for User entity
 * 
 * @author AF
 *
 */
public class CustomUserSerializer extends ApiGenericSerializer<CustomUser> {

	private static final long serialVersionUID = 1L;

	protected CustomUserSerializer() {
		super(CustomUser.class);
	}

	@Override
	public void serialize(CustomUser value, JsonGenerator gen, SerializerProvider provider) throws IOException {

		gen.writeStartObject();
		
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("username", value.getUsername());
		gen.writeStringField("email", value.getEmail());
		gen.writeStringField("firstName", value.getFirstName());
		writeIntegerField(gen,"age", value.getAge());
		writeDoubleField(gen,"weight", value.getWeight());
		writeIntegerField(gen,"height", value.getHeight());
		writeIntegerField(gen,"bmr", value.getBmr());
		writeIntegerField(gen,"tdee", value.getTdee());		
		
		gen.writeEndObject();
		
	}

}
