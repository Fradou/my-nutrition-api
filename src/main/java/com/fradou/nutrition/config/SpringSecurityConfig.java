package com.fradou.nutrition.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.endpoint.NimbusAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

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
	public static class AppSecurityConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		@Qualifier("customUserDetailsService")
		private UserDetailsService userDetailsService;

		private static List<String> clients = Arrays.asList("github", "google");
		
		private static String CLIENT_PROPERTY_KEY 
		  = "spring.security.oauth2.client.registration.";
		 
		@Autowired
		private Environment env;
		
		@Bean
		public OAuth2AuthorizedClientService authorizedClientService() {
		  
		    return new InMemoryOAuth2AuthorizedClientService(
		      clientRegistrationRepository());
		}
		
		@Bean
		public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> 
		  accessTokenResponseClient() {
		  
		    return new NimbusAuthorizationCodeTokenResponseClient();
		}
		
	    @Bean
	    public ClientRegistrationRepository clientRegistrationRepository() {
	        List<ClientRegistration> registrations = clients.stream()
	          .map(c -> getRegistration(c))
	          .filter(registration -> registration != null)
	          .collect(Collectors.toList());
	         
	        return new InMemoryClientRegistrationRepository(registrations);
	    }
	    
	    private ClientRegistration getRegistration(String client) {
	        String clientId = env.getProperty(
	          CLIENT_PROPERTY_KEY + client + ".client-id");
	     
	        if (clientId == null) {
	            return null;
	        }
	     
	        String clientSecret = env.getProperty(
	          CLIENT_PROPERTY_KEY + client + ".client-secret");
	      
	        if (client.equals("google")) {
	            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
	              .clientId(clientId).clientSecret(clientSecret).build();
	        }
	        if (client.equals("github")) {
	            return CommonOAuth2Provider.GITHUB.getBuilder(client)
	              .clientId(clientId).clientSecret(clientSecret).build();
	        }
	        return null;
	    }
		
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

					.and()
						.oauth2Login()
					    .loginPage("/login_oauth")
						.clientRegistrationRepository(clientRegistrationRepository())
						.authorizedClientService(authorizedClientService())
						.defaultSuccessUrl("/loginSuccess")
						.failureUrl("/loginFailure")
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
