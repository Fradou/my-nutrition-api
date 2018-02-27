package com.fradou.nutrition.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import com.fradou.nutrition.mvc.dao.interfaces.UserDAO;
import com.fradou.nutrition.mvc.entity.CustomUser;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		CustomUser user;
		
		try {
			user = userDao.findBy("username", username);
			
			if(user == null) {
				throw new UsernameNotFoundException("user not found");
			}
		}
		catch(Exception ex) {
			throw new UsernameNotFoundException("DB error");
		}
		
		return convertToSpringUser(user);
	}

	private UserDetails convertToSpringUser(CustomUser user) {
		
		User sprUser = new User(
				user.getUsername(),
				user.getPassword(),
				user.isEnabled(),
				user.isAccountNonExpired(),
				user.isCredentialsNonExpired(),
				user.isAccountNonLocked(),
				user.getAuthorities()
		);
		return sprUser;
	}

}
