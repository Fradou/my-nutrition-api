package com.fradou.nutrition.mvc.service;

import javax.mail.MessagingException;

public interface MailService {

	public void sendSimpleMessage(String to, String subject, String text);
	
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException;
}
