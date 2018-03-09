package com.fradou.nutrition.mvc.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.service.generic.GenericService;
import com.fradou.nutrition.mvc.utils.exception.InvalidDataCreationException;

@RestController
public abstract class GenericApiController<T extends GenericEntity> {

	@Autowired
	protected GenericService<T> service;

	protected void setService(GenericService<T> entityService) {
		this.service = entityService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<T> findAll(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "delta", defaultValue = "20", required = false) int delta) {
		Integer offest = (page - 1) * delta;

		return service.find(offest, delta);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public T findById(@PathVariable(value="id") String id) {
		Integer myId = Integer.valueOf(id);
		return service.find(myId);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Integer create(@Valid @RequestBody T newEntity,BindingResult validationResult) throws Exception {
		
		if(validationResult.hasErrors()) {
			throw new InvalidDataCreationException(newEntity.getClass(), validationResult.getFieldError().getField());
		}
		else {
			int id = service.create(newEntity);
			return id;
		}
	}
}
