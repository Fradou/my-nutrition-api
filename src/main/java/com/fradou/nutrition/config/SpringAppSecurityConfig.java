package com.fradou.nutrition.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.lang.Exception;

@Configuration
@EnableWebSecurity
@Order(2)
public class SpringAppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userDetailsService;
	
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
			.authorizeRequests()
			.antMatchers("/register").permitAll()
			.antMatchers("/**").authenticated()
			
			.and()
			.formLogin()
				// URL to custom login form
				.loginPage("/login")
				// URL to check login
				.loginProcessingUrl("/authenticateUser")
			//	.defaultSuccessUrl(defaultSuccessUrl)
				// Allow everyone to see login page
				.permitAll()
			
			.and()
				// Enable standard logout
				.logout()
				.permitAll()
			
			.and()
				.exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/resources/**");		
	}
}
