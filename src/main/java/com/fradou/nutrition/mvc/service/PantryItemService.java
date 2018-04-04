package com.fradou.nutrition.mvc.service;

import java.time.LocalDate;
import java.util.List;

import com.fradou.nutrition.mvc.entity.work.PantryItem;
import com.fradou.nutrition.mvc.service.generic.GenericService;

public interface PantryItemService extends GenericService <PantryItem> {

	public List<PantryItem> getNearlyExpiredItem(LocalDate referenceDate, Integer userId);

}
