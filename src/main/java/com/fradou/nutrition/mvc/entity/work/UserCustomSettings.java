package com.fradou.nutrition.mvc.entity.work;

import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter @Getter
@Component
public class UserCustomSettings {

	public boolean remindersAllowed;
	public Integer pantryDelayReminder;
	
}
