package com.fradou.nutrition.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;

@Entity
public class Food extends GenericEntity {

	public static String API_PATH = "food/";
	
	@Column
	private String name;
	
}
