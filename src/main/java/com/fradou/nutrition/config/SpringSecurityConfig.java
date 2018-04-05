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
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import com.fradou.nutrition.security.api.AuthenticationApiEntryPoint;
import com.fradou.nutrition.security.api.AuthenticationSuccessApiHandler;
import com.fradou.nutrition.security.api.CustomAccessDeniedHandler;

/**
 * Spring security setup. Configuration for user provider and url security
 * mapping.
 * 
 * @author AF
 */
@EnableWebSecurity
public class SpringSecurityConfig {

	/**
	 * Security config and mangement for api part
	 * @author AF
	 *
	 */
	@Configuration
	@Order(1)
	@EnableOAuth2Client
	public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		@Qualifier("customUserDetailsService")
		private UserDetailsService userDetailsService;

		@Autowired
		private AuthenticationApiEntryPoint authenticationApiEntryPoint;
		
		@Autowired
		private AuthenticationSuccessApiHandler authenticationSuccessApiHandler;
		
		@Autowired
		private CustomAccessDeniedHandler accessDeniedApiHandler;
		
		/**
		@Autowired
		private LogoutSuccessApiHandler logoutSuccessApiHandler;
		**/

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
				.antMatcher("/api/**").authorizeRequests()

				.antMatchers("/api/user/**")
					.hasRole("ADMIN")
					
				.antMatchers("/api/**")
					.authenticated()
					.and()

				.csrf().disable()
						
				.exceptionHandling()
					.authenticationEntryPoint(authenticationApiEntryPoint)
					.accessDeniedHandler(accessDeniedApiHandler)
					.and()
						
				.formLogin()
					.loginPage("/api/login")
					.successHandler(authenticationSuccessApiHandler)
					.permitAll()
					.and()
				
				.logout()
					.logoutUrl("/api/logout")
					.permitAll(); // .logoutSuccessHandler(logoutSuccessApiHandler)
		}
	}

	/**
	 * Security config and management for website part
	 * @author AF
	 *
	 */
	@Configuration
	@Order(2)
	@EnableOAuth2Client
	public static class AppSecurityConfig extends WebSecurityConfigurerAdapter {
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
				
				.antMatchers("/register")
					.permitAll()
					
				.antMatchers("/user/**")
					.hasRole("ADMIN")
				
				.antMatchers("/**")
					.authenticated()

					.and().formLogin()
					// URL to custom login form
					.loginPage("/login")
					// URL to check login
					.loginProcessingUrl("/authenticateUser")
					// .defaultSuccessUrl(defaultSuccessUrl)
					// Allow everyone to see login page
					.permitAll()

					.and()
					// Enable standard logout
					.logout().permitAll()

					.and().exceptionHandling().accessDeniedPage("/access-denied");
		}

		/**
		 * Ignore Spring security for resources folder. for all resources
		 */
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/resources/**");
		}
	}
}
