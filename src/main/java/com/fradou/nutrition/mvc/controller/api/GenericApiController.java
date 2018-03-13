package com.fradou.nutrition.mvc.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.entity.security.CustomUser;
import com.fradou.nutrition.mvc.service.UserService;
import com.fradou.nutrition.mvc.service.generic.GenericService;
import com.fradou.nutrition.mvc.utils.exception.InvalidDataCreationException;

/**
 * Generic controller that will be extend by all Controller managing
 * work/nutrition entities. Include standard Rest calls.
 * 
 * @author AF
 * @param <T>
 */
@RestController
public abstract class GenericApiController<T extends GenericEntity> {

	@Autowired
	protected GenericService<T> service;
	
	@Autowired
	protected UserService uService;

	protected void setService(GenericService<T> entityService) {
		this.service = entityService;
	}

	/**
	 * Get method to retrieve all entry of an entity.
	 * @param page
	 * @param delta
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<T> findAll(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "delta", defaultValue = "20", required = false) int delta) {
		Integer offest = (page - 1) * delta;

		return service.find(offest, delta, null);
	}
	
	/**
	 * Get method to retrieve a specific entry
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public T findById(@PathVariable(value="id") String id) {
		Integer myId = Integer.valueOf(id);
		return service.find(myId);
	}
	
	/**
	 * Post method to create new entry
	 * @param newEntity
	 * @param validationResult
	 * @return id new entry's id
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Integer create(@Valid @RequestBody T newEntity,BindingResult validationResult) {
		
		if(validationResult.hasErrors()) {
			throw new InvalidDataCreationException(newEntity.getClass(), validationResult.getFieldError().getField());
		}
		else {
			int id = service.create(newEntity);
			return id;
		}
	}
	
	/**
	 * Delete method for a specific entry
	 * @param id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") int id) {
		service.deleteById(id);
	}
	
	/**
	 * Put method to update an existing entity
	 * @param id
	 * @param entityUpdated
	 * @param validationResult
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") int id, @Valid @RequestBody T entityUpdated, BindingResult validationResult) {
		
		service.find(entityUpdated.getId());
	}
	
	protected CustomUser getCurrentUser(Authentication authenticate) {
		UserDetails userDetails = (CustomUser) authenticate.getPrincipal();		
		CustomUser user = uService.findUniqueBy("username", userDetails.getUsername());
		return user;
	}
	
	
	@RequestMapping("/test/")
	public CustomUser test(Authentication authenticate) {
		return getCurrentUser(authenticate);
	}
	
}
