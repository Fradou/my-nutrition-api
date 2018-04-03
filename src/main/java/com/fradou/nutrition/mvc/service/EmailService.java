package com.fradou.nutrition.mvc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Utils to send emails of all kinds
 * @author AF
 *
 */
@Component
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;
    
    @Value("${mail.sender}")
    public String emailFrom;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    
    /**
     * Basic email sender
     * @param to
     * @param subject
     * @param text
     */
    public void sendSimpleMessage(String to, String subject, String text) {
        
    	SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to);
        message.setFrom(emailFrom);
        message.setSubject(subject); 
        message.setText(text);
        try {
        	emailSender.send(message);
        }
        catch(MailException ex) {
        	LOGGER.error("Erreur rencontrée lors de la tentative d'envoi de mail :", ex);
        }
    }
}
