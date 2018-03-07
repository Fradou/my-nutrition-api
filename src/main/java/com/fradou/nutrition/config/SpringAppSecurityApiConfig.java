package com.fradou.nutrition.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.fradou.nutrition.security.api.AuthenticationApiEntryPoint;

@Configuration
@EnableWebSecurity
@Order(1)
public class SpringAppSecurityApiConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationApiEntryPoint authenticationApiEntryPoint;
	
	/**
	 * Use custom userProvider
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
				
		auth.userDetailsService(userDetailsService);
	}
	
	/**
	 * Setup custom login page and security mapping
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.exceptionHandling()
			.authenticationEntryPoint(authenticationApiEntryPoint)
			
			.and()
			.authorizeRequests()
			.antMatchers("/api/**")
			.authenticated()
			
			.and()
			.formLogin()
			
			.and()
			.logout()
			.permitAll();
	}
}
