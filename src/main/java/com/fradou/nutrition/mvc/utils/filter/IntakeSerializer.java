package com.fradou.nutrition.mvc.utils.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fradou.nutrition.mvc.entity.work.Intake;
import com.fradou.nutrition.mvc.entity.work.Meal;

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
			gen.writeEndObject();
		}
		gen.writeEndArray();
		gen.writeEndObject();
	}
}
