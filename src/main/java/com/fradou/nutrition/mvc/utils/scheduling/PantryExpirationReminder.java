package com.fradou.nutrition.mvc.utils.scheduling;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fradou.nutrition.config.Constant;
import com.fradou.nutrition.mvc.entity.security.CustomUser;
import com.fradou.nutrition.mvc.entity.work.PantryItem;
import com.fradou.nutrition.mvc.service.impl.MailServiceImpl;
import com.fradou.nutrition.mvc.service.impl.PantryItemServiceImpl;
import com.fradou.nutrition.mvc.service.impl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PantryExpirationReminder {
	
	@Autowired
	PantryItemServiceImpl pService;
	
	@Autowired
	UserServiceImpl uService;
	
	@Autowired
    MailServiceImpl mService;
	
	@Scheduled(cron = "0 0 5 * * *")
	public void pantryExpirationCheck() {
		
		log.info("========== Cron : ExpirationCheck =>> START ==========");		
		
		List<CustomUser> users = uService.findAllBy("reminders", true);
		LocalDate today = LocalDate.now();
		log.info("Found " + users.size() + " users with reminders activated.");
		
		for(CustomUser user : users) {
			
			int userId = user.getId();
			int userDelayReminder = user.getPantryDelayReminder();
			LocalDate reminderDate = today.plusDays(userDelayReminder);
			
			log.debug("Search userId:" + userId + " - peremption :" + reminderDate);
			List<PantryItem> pantryItems = pService.getNearlyExpiredItem(reminderDate, userId);
			log.debug(pantryItems.size() + " results found.");
			if(pantryItems.size() > 0) {
				String concatItems = pantryItems.stream().map(
						pi -> pi.getFood().getName() + " : " + pi.getExpirationDate().toString()).collect(Collectors.joining(", ")
				);
				log.debug("Sending email");
				mService.sendSimpleMessage(user.getEmail(), Constant.EMAIL_PANTRY_PEREMPTION_REMINDER, concatItems);
			}
		}
		
		log.info("========== Cron : ExpirationCheck =>> STOP ==========");
	}
	
	@Scheduled(cron = "0 0 4 * * *")
	public void pantryExpiredDeletion() {

		log.info("========== Cron : ExpiredDeletion =>> START ==========");
		
		List<CustomUser> users = uService.findAllBy("autoDeleteAfterExpiration", true);
		LocalDate today = LocalDate.now();
		log.info("Found " + users.size() + " users with auto suppression.");
		
		for(CustomUser user : users) {
			int userId = user.getId();
			pService.deleteExpiredItem(today, userId);		
		}
		
		log.info("========== Cron : ExpiredDeletion =>> STOP ==========");
	}
	
}
