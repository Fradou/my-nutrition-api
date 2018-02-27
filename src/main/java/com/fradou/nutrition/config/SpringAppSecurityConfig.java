package com.fradou.nutrition.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.lang.Exception;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringAppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource securityDataSource;
	
	/**
	 * Setup in memory authentification
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// UserBuilder users = User.withDefaultPasswordEncoder();
		
		// auth.inMemoryAuthentication()
		//	.withUser(users.username("test").password("test").roles("USER"))
		//	.withUser(users.username("test2").password("test2").roles("USER", "MANAGER"))
		//	.withUser(users.username("test3").password("test3").roles("USER", "MANAGER", "ADMIN"));
		
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}
	
		/**
	 * Setup custom login page
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/securePage/**").hasRole("MANAGER")
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
				.permitAll()
			.and()
				.exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
}
