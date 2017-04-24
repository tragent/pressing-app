package com.tragent.pressing.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tragent.pressing.model.Category;
import com.tragent.pressing.model.Item;
import com.tragent.pressing.service.CategoryService;
import com.tragent.pressing.service.ItemService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ItemService itemService;
	
	/**
	 * Get all categories or category with a given name.
	 * 
	 * @param categoryName
	 * @return collection of categories or category with the given name
	 */
	@RequestMapping(method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Category>> getCategories(@RequestParam(value = "categoryName", required = false) String categoryName){
		
		Collection<Category> categories = new ArrayList<>();
		if (categoryName != null) {
			Category category = categoryService.findByName(categoryName);
			categories.add(category);
			
		} else {
			Collection<Category> allCategories = categoryService.findAll();
			categories.addAll(allCategories);
		}
		
		return new ResponseEntity<>(categories, HttpStatus.OK);	
	}
	
	/**
	 * Get category with given category id.
	 * 
	 * @param categoryId
	 * @return Category object or 404 if category is not found
	 */
	@RequestMapping(value="/{categoryId}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Long categoryId){
		
		Category category = categoryService.findById(categoryId);
		if (category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	/**
	 * Get items with same category.
	 * 
	 * @param categoryId
	 * @return collection of items
	 */
	@RequestMapping(value="/{categoryId}/items",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Item>> getCategoryItems(@PathVariable("categoryId") Long categoryId){
		
		Collection<Item> items = itemService.findCategoryItems(categoryId);		
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	/**
	 * Create new category.
	 * 
	 * @param category
	 * @return Category Object (created object)
	 */
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
		
		category = categoryService.create(category);
		if (category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(category, HttpStatus.CREATED);	
	}
	
	/**
	 * Update category.
	 * 
	 * @param category
	 * @return Category Object (updated object).
	 */
	@RequestMapping(value="/{categoryId}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> updateCategory(@RequestBody Category category){
		
		category = categoryService.update(category);
		if (category == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
}
