package com.tragent.pressing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tragent.pressing.model.CustomUser;
import com.tragent.pressing.service.UserService;

import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("api/v1/authenticate")
public class AuthenticationController {
	
	@Autowired
	private UserService userService;

	/**
	 * Authenticate user
	 * 
	 * @param username
	 * @return Logged in user
	 */
	@RequestMapping(method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomUser> getCategories(){
		CustomUser loggedInUser = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		/*String username = loggedInUser.getUsername();
		String password = loggedInUser.getPassword();
		String plainClientCredentials= username + ':' + password;
		String base64EncodedAuthenticationKey = Base64.encodeBase64String(plainClientCredentials.getBytes());
		
		LoggedInUser user = new LoggedInUser(loggedInUser, base64EncodedAuthenticationKey);*/
		
		return new ResponseEntity<>(loggedInUser, HttpStatus.OK);	
	}
}
