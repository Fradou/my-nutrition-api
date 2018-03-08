package com.fradou.nutrition.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fradou.nutrition.mvc.entity.security.CustomUser;
import com.fradou.nutrition.mvc.entity.security.Role;
import com.fradou.nutrition.mvc.service.RoleService;
import com.fradou.nutrition.mvc.service.UserService;

@Controller
public class SecurityController {
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private RoleService rService;

	@GetMapping("/login")
	public String login() {
		return "customLogin";
	}
	
	@GetMapping("/access-denied")
	public String accessDeniedPage() {
		return "accessDenied";
	}
	
	@GetMapping(value="/register")
	public String registerUser(Model model) {
		
		CustomUser user = new CustomUser();
		model.addAttribute("newUser", user);
		
		return "user/registration";
	}
	
	@PostMapping(value="/register")
	public String registerUserValidation(Model model,@Valid @ModelAttribute("newUser") CustomUser user, BindingResult validationResult) {
		
		boolean hasError = false;
		
		if(validationResult.hasErrors()) {
			hasError = true;
		}
		
		if(uService.alreadyExists("username",user.getUsername())) {
			validationResult.addError(new FieldError("newUser", "username", "Username déjà utilisé dommage!"));
			hasError = true;
		}
		
		if(uService.alreadyExists("email", user.getEmail())) {
			validationResult.addError(new FieldError("newUser", "email", "Email déjà utilisé dommage!"));
			hasError = true;
		}
		
		if(hasError) {
			return "user/registration";
		}
		
		setupUser(user);
		uService.create(user);
		
		return "user/welcome";
	}
	
	/**
	 * Method used to treat user data before persistance
	 * @param user
	 */
	private void setupUser(CustomUser user) {
		
		// Password hash
		String hash_pw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword("{bcrypt}" + hash_pw);
		
		// User disable by default user
		user.setEnabled(false);
		
		// Give basic use role
		Role role = rService.findUniqueBy("authority", "ROLE_USER");
		user.addRole(role);
	}
}
