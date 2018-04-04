package com.fradou.nutrition.mvc.service;

import java.time.LocalDate;
import java.util.List;

import com.fradou.nutrition.mvc.entity.work.PantryItem;
import com.fradou.nutrition.mvc.service.generic.GenericEntityService;

public interface PantryItemService extends GenericEntityService<PantryItem> {

	public List<PantryItem> getNearlyExpiredItem(LocalDate referenceDate, Integer userId);

	public void deleteExpiredItem(LocalDate referenceDate, Integer userId);
}
