package com.fradou.nutrition.mvc.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.nutrition.mvc.entity.work.Intake;

@RequestMapping("/intake")
@RestController
public class IntakeApiController extends GenericApiController<Intake> {

}
