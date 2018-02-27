package com.fradou.nutrition.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import java.lang.Exception;

@Configuration
@EnableWebSecurity
public class SpringAppSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Setup in memory authentification
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("test").password("test").roles("USER"));
	}
	
		/**
	 * Setup custom login page
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.formLogin()
				// URL to custom login form
				.loginPage("/login")
				// URL to check login
				.loginProcessingUrl("/authenticateUser")
				// Allow everyone to see login page
				.permitAll()
			.and()
				// Enable standard logout
				.logout()
				.permitAll();
	}
}
