package com.fradou.nutrition.mvc.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.nutrition.mvc.entity.work.PantryItem;

@RequestMapping("/api/pantryItem")
@RestController
public class PantryItemApiController extends GenericApiController<PantryItem> {

}
