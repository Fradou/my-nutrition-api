package com.fradou.nutrition.mvc.controller.api;

import com.fradou.nutrition.mvc.controller.api.generic.GenericApiController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.nutrition.mvc.entity.work.Intake;

/**
 * Controller in charge of Intake calls (nutrition entity)
 * @author AF
 */
@RequestMapping("/api/intake")
@RestController
public class IntakeApiController extends GenericApiController<Intake> {

	protected String setDefaultEntityGraph() {
		return "graph.IntakeMeal";
	}
	
	@Override
	protected boolean setUserDependance() {
		return true;
	}
}
