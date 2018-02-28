package com.fradou.nutrition.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;

import com.fradou.nutrition.mvc.dao.interfaces.UserDAO;
import com.fradou.nutrition.mvc.entity.CustomUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDao;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("Dans custom service detail");
		System.out.println("Username à recherche : " + username);
		CustomUser user;
		
		try {
			user = userDao.findBy("username", username);
			
			if(user == null) {
				throw new UsernameNotFoundException("user not found");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new UsernameNotFoundException("DB error");
		}
		System.out.println("User trouvé on va convertir");
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
