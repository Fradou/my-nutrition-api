package com.fradou.nutrition.mvc.utils.serializer.generic;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.utils.hateoas.RelationType;

public abstract class ApiGenericSerializer<T extends GenericEntity> extends StdSerializer<T> {

	private static final long serialVersionUID = 1L;
	
	protected ApiGenericSerializer(Class<T> t) {
		super(t);
	}
	
	public void testons() {
		
	}
	
	protected void setLinks(GenericEntity entity, JsonGenerator gen) throws IOException {
		
		gen.writeArrayFieldStart("links");
		gen.writeStartObject();
			gen.writeStringField("rel", RelationType.LIST.toString());
			gen.writeStringField("href", entity.getLinks(RelationType.LIST));
		gen.writeEndObject();
		gen.writeStartObject();
			gen.writeStringField("rel", RelationType.SELF.toString());
			gen.writeStringField("href", entity.getLinks(RelationType.SELF));
		gen.writeEndObject();
		gen.writeEndArray();
	}
}