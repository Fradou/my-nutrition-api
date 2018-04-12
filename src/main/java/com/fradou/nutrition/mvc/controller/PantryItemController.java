package com.fradou.nutrition.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fradou.nutrition.mvc.service.PantryItemService;

@Controller
@RequestMapping("/pantry")
public class PantryItemController {

	@Autowired
	private PantryItemService pService;
	
	@GetMapping
	public String indexPantry(Model model) {
		model.addAttribute("items", pService.findAll("graph.PantryItemFood"));
		return "pantry/item-list";
	}	
}
