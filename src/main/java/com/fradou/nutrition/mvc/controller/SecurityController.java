package com.fradou.nutrition.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fradou.nutrition.mvc.entity.CustomUser;
import com.fradou.nutrition.mvc.service.UserService;

@Controller
public class SecurityController {
	
	@Autowired
	private UserService uService;

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
		
		
		user.setPassword("{noop}" + user.getPassword());
		uService.create(user);
		
		return "user/welcome";
	}
}
