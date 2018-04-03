package com.fradou.nutrition.mvc.utils.scheduling;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fradou.nutrition.mvc.entity.security.CustomUser;
import com.fradou.nutrition.mvc.entity.work.PantryItem;
import com.fradou.nutrition.mvc.service.PantryItemService;
import com.fradou.nutrition.mvc.service.UserService;

@Component
public class PantryExpirationReminder {

	private static final Logger LOGGER = LoggerFactory.getLogger(PantryExpirationReminder.class);
	
	@Autowired
	PantryItemService pService;
	
	@Autowired
	UserService uService;
	
	@Scheduled(cron = "1 * * * * *")
	public void pantryExpirationCheck() {
		
		LOGGER.error("========== Cron =>> Start ==========");		
		// Get user list allowing reminder and get their settings
		
		List<CustomUser> users = uService.findAllBy("reminders", true);
		LocalDate today = LocalDate.now();
		System.out.println("Ai trouvé " + users.size() + " utilisateurs qui veulent des reminders.");
		
		for(CustomUser user : users) {
			
			int userId = user.getId();
			int userDelayReminder = user.getPantryDelayReminder();
			LocalDate reminderDate = today.plusDays(userDelayReminder);
			
			System.out.println("Search pour userId:" + userId + " des elements se périssant le " + reminderDate);
			List<PantryItem> pantryItems = pService.getNearlyExpiredItem(reminderDate, userId);
			System.out.println("Ai trouvé " + pantryItems.size() + " résultats.");
			if(pantryItems.size() > 0) {
				String concatItems = pantryItems.stream().map(
						pi -> pi.getFood().getName() + " : " + pi.getExpirationDate().toString()).collect(Collectors.joining(",")
				);
				System.out.println(concatItems);
			}
		}
		
		// Send email
		
		// Option recipe
		LOGGER.error("========== Cron =>> Finish ==========");
	}
}
