package com.fradou.nutrition.mvc.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fradou.nutrition.mvc.entity.CustomUser;
import com.fradou.nutrition.mvc.entity.Role;
import com.fradou.nutrition.mvc.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService uService;

	@GetMapping(value="/profile")
	public String userProfile(Model model, Principal principal) {
		
		CustomUser user = uService.findUniqueBy("username", principal.getName());
		model.addAttribute("currentUser", user);
		
		return "profile";
	}
	
	@PostMapping("/profile")
	public String editProfile(@ModelAttribute("currentUser") CustomUser user, Authentication authentication) {
		System.out.println("in save");
		if(authentication.getName().equals(user.getUsername())) {
			System.out.println("va update");
			uService.update(user);
		}
		else {
			if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Admin"))) {
				System.out.println("va update");
				uService.update(user);
			}
		}
		
		return "redirect:/user/profile";
	}
}
