package com.fradou.nutrition.mvc.utils.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.service.PantryItemService;

@Component
public class PantryExpirationReminder {

	private static final Logger LOGGER = LoggerFactory.getLogger(PantryExpirationReminder.class);
	
	@Autowired
	PantryItemService pService;
	
	@Scheduled(cron = "0 59 11 * * *")
	public void pantryExpirationCheck() {
		

		LOGGER.error("Triggered : c'est le Cron");
		// Get user list allowing reminder and get their settings
		
		// Look for food going expired accoring to setting
		
		// Send email
		
		// Option recipe
		
		// Log
	}
}
