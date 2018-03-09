package com.fradou.nutrition.mvc.entity.work;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fradou.nutrition.mvc.entity.security.CustomUser;
import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;

@Entity
@Component
@Table(name="intake")
public class Intake extends GenericEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	@NotNull
	private LocalDate date; 
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private CustomUser user;
	
	@Override
	protected String initializeEntityPath() {
		return "/intake";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CustomUser getUser() {
		return user;
	}

	public void setUser(CustomUser user) {
		this.user = user;
	}
}
