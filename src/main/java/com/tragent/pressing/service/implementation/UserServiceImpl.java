package com.tragent.pressing.service.implementation;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tragent.pressing.model.CustomUser;
import com.tragent.pressing.repository.UserRepository;
import com.tragent.pressing.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	@Secured("ROLE_ADMINISTRATION")
	public Collection<CustomUser> findAll() {
		
		List<CustomUser> users = userRepository.findAll();
		return users;
	}

	@Override
	public CustomUser findById(Long id) {
		
		CustomUser user = userRepository.findOne(id);
		return user;
	}

	@Override
	public CustomUser findByUserName(String username) {
		
		CustomUser user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	public Collection<CustomUser> findByIsActive(boolean isActive) {
		
		Collection<CustomUser> users = userRepository.findByIsActive(isActive);
		return users;
	}

	@Override
	@Secured("ROLE_ADMINISTRATION")
	public CustomUser create(CustomUser user) {
		
		if (findByUserName(user.getUsername()) != null) {
			return null;
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		CustomUser savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public CustomUser update(CustomUser user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		CustomUser savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	@Secured("ROLE_ADMINISTRATION")
	public void deactivate(Long id) {
		
		CustomUser user = findById(id);
		user.setActive(false);
	}

}
