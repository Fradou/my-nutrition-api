package com.fradou.nutrition.mvc.controller.api.generic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.entity.security.CustomUser;
import com.fradou.nutrition.mvc.service.UserService;
import com.fradou.nutrition.mvc.service.generic.GenericService;
import com.fradou.nutrition.mvc.utils.exception.InvalidDataCreationException;
import com.fradou.nutrition.mvc.utils.exception.NotBelongingToUserException;
import com.fradou.nutrition.mvc.utils.user.PrimaryRole;

/**
 * Generic controller that will be extend by all Controller managing
 * work/nutrition entities. Include standard Rest calls.
 * 
 * @author AF
 * @param <T>
 */
@RestController
public abstract class GenericApiController<T extends GenericEntity> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericApiController.class);
	
	@Autowired
	protected GenericService<T> service;
	
	@Autowired
	protected UserService uService;

	protected String defaultEntityGraph;
	
	protected boolean isUserDependant;
	
	/**
	 * Abstract method to set the default entityGraph to retrieve entities.
	 * 
	 * @return
	 */
	protected abstract String setDefaultEntityGraph();
	
	/**
	 * Abstract method to set dependency between controller's entity and users
	 * (change much of the verification logic)
	 * 
	 * @return
	 */
	protected abstract boolean setUserDependance();
	
	/**
	 * No-arg construct using the default graph setter.
	 */
	public GenericApiController() {
		defaultEntityGraph = setDefaultEntityGraph();
		isUserDependant = setUserDependance();
	}

	/**
	 * Get method to retrieve all entry of an entity.
	 * @param page
	 * @param delta
	 * @return
	 */
	@GetMapping
	public List<T> findAll(
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "20", required = false) int results,
			Authentication authenticate) {
		
		Integer userId = null;
		
		if(isUserDependant) {
			CustomUser user = getCurrentUser(authenticate);
			userId= user.getId();
		}
		
		return service.find(userId, null, null, page, results, defaultEntityGraph);
	}
	
	/**
	 * Get method to retrieve a specific entry
	 * @param id
	 * @return
	 */
	@GetMapping("{id}")
	public T findById(@PathVariable int id, Authentication authenticate) {
		
		T entity = service.find(id, defaultEntityGraph);
		
		if(isUserDependant) {
			CustomUser user = getCurrentUser(authenticate);
			if(!service.belongToUser(entity, user.getId())){
				throw new NotBelongingToUserException(entity.getClass(), HttpMethod.GET);
			}
		}
		return entity;
	}
	
	/**
	 * Post method to create new entry
	 * @param newEntity
	 * @param validationResult
	 * @return id new entry's id
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer create(@Valid @RequestBody T newEntity,BindingResult validationResult, Authentication authenticate, HttpServletRequest request) {
		
		if(validationResult.hasErrors()) {
			throw new InvalidDataCreationException(newEntity.getClass(), validationResult.getFieldError().getField());
		}
		
		if(isUserDependant) {
			int user_id = getCurrentUser(authenticate).getId();
			if(! service.belongToUser(newEntity, user_id)){
				throw new NotBelongingToUserException(newEntity.getClass(), HttpMethod.POST);
			}
			else {
				int id = service.create(newEntity);
				return id;
			}
		}
		else {
			if(request.isUserInRole(PrimaryRole.ROLE_ADMIN.toString())) {
				int id = service.create(newEntity);
				return id;
			}
			else {
				throw new NotBelongingToUserException(newEntity.getClass(), HttpMethod.POST);
			}
		}
	}
	
	/**
	 * Delete method for a specific entry
	 * @param id
	 * @throws Exception 
	 */
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable int id, Authentication authenticate, HttpServletRequest request) throws Exception {
		
		
		if(isUserDependant) {
			int user_id = getCurrentUser(authenticate).getId();
			T entity = service.find(id);
			if(service.belongToUser(entity, user_id)) {
				service.delete(entity);
			}
			else {
				throw new NotBelongingToUserException(entity.getClass(), HttpMethod.DELETE);
			}
		}
		else {
			if(request.isUserInRole(PrimaryRole.ROLE_ADMIN.toString())) {
				service.deleteById(id);
			}
			else { 
				throw new NotBelongingToUserException();
			}
		}
	}
	
	/**
	 * Put method to update an existing entity
	 * @param id
	 * @param entityUpdated
	 * @param validationResult
	 */
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable int id, @Valid @RequestBody T entityUpdated, BindingResult validationResult) {
		
		service.find(entityUpdated.getId());
	}
	
	/**
	 * Utils to get authenticate CustomUser.
	 * @param authenticate
	 * @return
	 */
	protected CustomUser getCurrentUser(Authentication authenticate) {
		UserDetails userDetails = (UserDetails) authenticate.getPrincipal();		
		CustomUser user = uService.findUniqueBy("username", userDetails.getUsername());
		return user;
	}	
}
