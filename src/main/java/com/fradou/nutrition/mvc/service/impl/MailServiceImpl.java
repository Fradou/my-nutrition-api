package com.fradou.nutrition.mvc.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * Utils to send emails of all kinds
 * @author AF
 *
 */
@Component
public class MailServiceImpl {

    @Autowired
    public JavaMailSender emailSender;
    
    @Value("${mail.sender}")
    public String emailFrom;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);
    
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
    
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {
         
    	MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);
        
        try {
        	emailSender.send(message);
        }
        catch(MailException ex) {
        	LOGGER.error("Erreur rencontrée lors de la tentative d'envoi de mail :", ex);
        }
    }
}
