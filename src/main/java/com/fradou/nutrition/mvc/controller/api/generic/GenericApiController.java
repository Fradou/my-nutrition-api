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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.nutrition.mvc.controller.api.ExceptionHandlerApiController;
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
	
	protected boolean userDependant;
	
	/**
	 * Abstract method to set the default entityGraph to retrieve entities.
	 * @return
	 */
	protected abstract String setDefaultEntityGraph();
	
	protected abstract boolean setUserDependant();
	
	/**
	 * No-arg construct using the default graph setter.
	 */
	public GenericApiController() {
		defaultEntityGraph = setDefaultEntityGraph();
		userDependant = setUserDependant();
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
			@RequestParam(value = "results", defaultValue = "20", required = false) int results,
			Authentication authenticate) {
		
		Integer userId = null;
		
		if(userDependant) {
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
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public T findById(@PathVariable(value="id") int id, Authentication authenticate) {
		
		T entity = service.find(id, defaultEntityGraph);
		
		if(userDependant) {
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
	 * @throws Exception 
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") int id, Authentication authenticate, HttpServletRequest request) throws Exception {
		
		
		if(userDependant) {
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
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") int id, @Valid @RequestBody T entityUpdated, BindingResult validationResult) {
		
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
