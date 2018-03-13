package com.fradou.nutrition.mvc.utils.serializer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fradou.nutrition.mvc.entity.work.Intake;
import com.fradou.nutrition.mvc.entity.work.Meal;
import com.fradou.nutrition.mvc.utils.hateoas.RelationType;

@Component
public class IntakeSerializer extends StdSerializer<Intake> {

	private static final long serialVersionUID = 1L;

	protected IntakeSerializer() {
		super(Intake.class);
	}

	@Override
	public void serialize(Intake value, JsonGenerator gen, SerializerProvider provider) throws IOException {

		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("intakeDate", value.getIntakeDate().toString());
		gen.writeArrayFieldStart("meals");
		for(Meal meal : value.getMeals().values()) {
			gen.writeStartObject();
			gen.writeNumberField("id", meal.getId());
			gen.writeStringField("mealType", meal.getMealType().toString());
			gen.writeArrayFieldStart("links");
				gen.writeStartObject();
					gen.writeStringField("rel", RelationType.LIST.toString());
					gen.writeStringField("href", meal.getLinks().get(RelationType.LIST).toString());
				gen.writeEndObject();
				gen.writeStartObject();
					gen.writeStringField("rel", RelationType.SELF.toString());
					gen.writeStringField("href", meal.getLinks().get(RelationType.SELF).toString());
				gen.writeEndObject();
			gen.writeEndArray();
			gen.writeEndObject();
		}
		gen.writeEndArray();
		gen.writeEndObject();
	}
}
