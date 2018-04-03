package com.fradou.nutrition.mvc.utils.scheduling;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fradou.nutrition.config.Constant;
import com.fradou.nutrition.mvc.entity.security.CustomUser;
import com.fradou.nutrition.mvc.entity.work.PantryItem;
import com.fradou.nutrition.mvc.service.EmailServiceImpl;
import com.fradou.nutrition.mvc.service.PantryItemService;
import com.fradou.nutrition.mvc.service.UserService;

@Component
public class PantryExpirationReminder {

	private static final Logger LOGGER = LoggerFactory.getLogger(PantryExpirationReminder.class);
	
	@Autowired
	PantryItemService pService;
	
	@Autowired
	UserService uService;
	
	@Autowired
    EmailServiceImpl mService;
	
	@Scheduled(cron = "1 * * * * *")
	public void pantryExpirationCheck() {
		
		LOGGER.info("========== Cron =>> Start ==========");		
		// Get user list allowing reminder and get their settings
		
		List<CustomUser> users = uService.findAllBy("reminders", true);
		LocalDate today = LocalDate.now();
		LOGGER.info("Ai trouvé " + users.size() + " utilisateurs qui veulent des reminders.");
		
		for(CustomUser user : users) {
			
			int userId = user.getId();
			int userDelayReminder = user.getPantryDelayReminder();
			LocalDate reminderDate = today.plusDays(userDelayReminder);
			
			LOGGER.info("Search userId:" + userId + " - peremption :" + reminderDate);
			List<PantryItem> pantryItems = pService.getNearlyExpiredItem(reminderDate, userId);
			LOGGER.info(pantryItems.size() + " résults found.");
			if(pantryItems.size() > 0) {
				String concatItems = pantryItems.stream().map(
						pi -> pi.getFood().getName() + " : " + pi.getExpirationDate().toString()).collect(Collectors.joining(", ")
				);
				LOGGER.info(concatItems);
				mService.sendSimpleMessage(user.getEmail(), Constant.EMAIL_PANTRY_PEREMPTION_REMINDER, concatItems);
			}
		}
		
		LOGGER.info("========== Cron =>> Finish ==========");
	}
}
