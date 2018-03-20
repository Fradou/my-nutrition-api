package com.fradou.nutrition.mvc.controller.api;

import com.fradou.nutrition.mvc.controller.api.generic.GenericApiController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.nutrition.mvc.entity.work.PantryItem;

/**
 * Controller in charge of PantryItem calls (nutrition entity)
 * 
 * @author AF
 *
 */
@RequestMapping("/api/pantryItem")
@RestController
public class PantryItemApiController extends GenericApiController<PantryItem> {

	protected String setDefaultEntityGraph() {
		return "graph.PantryItemFood";
	}
	
	@Override
	protected boolean setUserDependance() {
		return true;
	}
}
