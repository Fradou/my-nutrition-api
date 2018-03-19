package com.fradou.nutrition.mvc.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fradou.nutrition.mvc.utils.work.ApiErrorMessage;

@ControllerAdvice
public class ExceptionHandlerGlobalController {

	private static Pattern pattern;
	
	private static Matcher matcher;
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView noHandlerFoundException(NoHandlerFoundException ex, Model model) {
		pattern = Pattern.compile("/api/");
		matcher = pattern.matcher(ex.getRequestURL());
		
		ModelAndView mav = new ModelAndView();
		if(matcher.find()) {
			MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
			mappingJackson2JsonView.setExtractValueFromSingleKeyModel(true);
			mav.setView(mappingJackson2JsonView);
			mav.addObject(new ApiErrorMessage(HttpStatus.NOT_FOUND.value(), "Unable to find anything at : " + ex.getRequestURL()));
		}
		else {
			mav.setViewName("404");
		}
		return mav;
	}
}
