package com.tragent.pressing.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tragent.pressing.model.CustomUser;
import com.tragent.pressing.repository.UserRepository;

/**
 * Custom UserDetail service
 */
public class CustomUserDetailService implements UserDetailsService {
	
	private UserRepository userRepository;
	final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public CustomUserDetailService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		CustomUser user = userRepository.findByUsername(username);
		if (user == null || user.isActive() == false) {
			throw new UsernameNotFoundException(username);
		}
		User foundUser = new User(user.getUsername(), user.getPassword(), user.isActive(), true, true, 
		          true, user.getRoles());	
		return foundUser;
	}

}
