package com.fradou.nutrition.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fradou.nutrition.mvc.dao.PantryItemDAOImpl;
import com.fradou.nutrition.mvc.entity.work.PantryItem;
import com.fradou.nutrition.mvc.service.generic.GenericServiceImpl;

@Service
@Transactional(rollbackFor = Exception.class)
public class PantryItemService extends GenericServiceImpl<PantryItem, PantryItemDAOImpl> {

}
