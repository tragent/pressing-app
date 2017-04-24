package com.tragent.pressing.service;

import java.util.Collection;

import com.tragent.pressing.model.CustomUser;

/**
 * Service that provides CRUD operations for users
 **/
public interface UserService {

	/**
	 * Get all users in the system.
	 * 
	 * @return Collection of all users 
	 */
	public Collection<CustomUser> findAll();
	
	/**
	 * Find an user by Id.
	 * 
	 * @param id
	 * @return CustomUser object if found, else return null
	 */
	public CustomUser findById(Long id);
	
	/**
	 * Find an user by username.
	 * 
	 * @param username
	 * @return CustomUser object if found, else return null
	 */
	public CustomUser findByUserName(String username);
	
	/**
	 * Find users with active accounts.
	 * 
	 * @param isActive
	 * @return Collection of users
	 */
	public Collection<CustomUser> findByIsActive(boolean isActive);
	
	/**
	 * Create new user.
	 * 
	 * @param user
	 * @return CustomUser object (Created user object)
	 */
	public CustomUser create(CustomUser user);
	
	/**
	 * Update an existing user's information.
	 * 
	 * @param user
	 * @return CustomUser object (Updated user object)
	 */
	public CustomUser update(CustomUser user);
	
	/**
	 * Deactivate a user's account.
	 * 
	 * @param id
	 */
	public void deactivate(Long id);
	
}
