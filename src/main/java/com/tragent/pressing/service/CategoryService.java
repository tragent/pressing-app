package com.tragent.pressing.service;

import java.util.Collection;

import com.tragent.pressing.model.Category;

/**
 * Service that provides CRUD operations for categories
 **/
public interface CategoryService {
	
	/**
	 * Get all categories in the system.
	 * 
	 * @return Collection of categories
	 */
	public Collection<Category> findAll();
	
	/**
	 * Find a category by Id.
	 * 
	 * @param id
	 * @return Category object if found else return null
	 */
	public Category findById(Long id);
	
	/**
	 * Find a category by name.
	 * 
	 * @param name
	 * @return Category object if found else return null
	 */
	public Category findByName(String name);
		
	/**
	 * Create new Category.
	 * 
	 * @param category
	 * @return Category object (created Category object) 
	 */
	public Category create(Category category);
	
	/**
	 * Update an existing category's information.
	 * 
	 * @param category
	 * @return Category object (updated Category object)
	 */
	public Category update(Category category);
	
	/**
	 * Delete a category from the system.
	 * 
	 * @param id
	 */
	public void delete(Long id);

}
