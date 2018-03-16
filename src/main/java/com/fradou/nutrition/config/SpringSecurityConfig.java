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

import com.fradou.nutrition.security.api.AuthenticationApiEntryPoint;
import com.fradou.nutrition.security.api.AuthenticationSuccessApiHandler;

/**
 * Spring security setup. Configuration for user provider and url security
 * mapping.
 * 
 * @author AF
 */
@EnableWebSecurity
public class SpringSecurityConfig {

	@Configuration
	@Order(1)
	public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		@Qualifier("customUserDetailsService")
		private UserDetailsService userDetailsService;

		@Autowired
		private AuthenticationApiEntryPoint authenticationApiEntryPoint;
		
		@Autowired
		private AuthenticationSuccessApiHandler authenticationSuccessApiHandler;
		
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
					.antMatcher("/api/user/**")
						.authorizeRequests()
						.anyRequest()
						.hasRole("Admin")
						.and()
						
					.antMatcher("/api/**")
						.authorizeRequests()
						.anyRequest()
						.authenticated()
						.and()
					
					.csrf()
						.disable()
						.exceptionHandling()
						.authenticationEntryPoint(authenticationApiEntryPoint)
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

	@Configuration
	@Order(2)
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

			http.authorizeRequests().antMatchers("/register").permitAll().antMatchers("/**").authenticated()

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

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/resources/**");
		}
	}
}
