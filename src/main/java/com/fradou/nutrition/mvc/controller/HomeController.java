package com.fradou.nutrition.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private static String authorizationRequestBaseUri = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
	
	@RequestMapping("/login_oauth")
	public String getLogin(Model model) {
	
	    Iterable<ClientRegistration> clientRegistrations = null;
	    ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository).as(Iterable.class);
	    if (type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
	        clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
	    }
	 
	    clientRegistrations.forEach(registration -> oauth2AuthenticationUrls.put(registration.getClientName(), 
	    		authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
	    model.addAttribute("urls", oauth2AuthenticationUrls);
	    
		return "login_oauth";
	}
	
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	 
	@GetMapping("/loginSuccess")
	public String getLoginInfo(Model model, OAuth2AuthenticationToken authentication) {
	    OAuth2AuthorizedClient client = authorizedClientService
	      .loadAuthorizedClient(
	        authentication.getAuthorizedClientRegistrationId(), 
	          authentication.getName());
	    //...
	    return "loginSuccess";
	}
}
