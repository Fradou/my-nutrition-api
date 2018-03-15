package com.fradou.nutrition.mvc.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.nutrition.mvc.entity.security.CustomUser;

@RestController
@RequestMapping("/api/user")
public class UserApiController extends GenericApiController<CustomUser> {

	protected String setDefaultEntityGraph() {
		return null;
	}
}
