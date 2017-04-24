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

import com.tragent.pressing.model.Item;
import com.tragent.pressing.service.ItemService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("api/v1/items")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * Get all items or item with a given name.
	 * 
	 * @param itemName
	 * @return Collection of items or item with the given name
	 */
	@RequestMapping(method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Item>> getItems(@RequestParam(value = "itemName", required = false) String itemName){
		
		Collection<Item> items = new ArrayList<>();
		if (itemName != null) {
			Item item = itemService.findByName(itemName);
			items.add(item);
			
		} else {
			Collection<Item> allCategories = itemService.findAll();
			items.addAll(allCategories);
		}
		
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	/**
	 * Get Item with given item id.
	 * 
	 * @param itemId
	 * @return Item object or 404 if item is not found
	 */
	@RequestMapping(value="/{itemId}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> getItemById(@PathVariable("itemId") Long itemId){
		
		Item item = itemService.findById(itemId);
		if (item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	/**
	 * Create new item.
	 * 
	 * @param item
	 * @return Item object (created item object)
	 */
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> createItem(@RequestBody Item item){
		
		item = itemService.create(item);
		if (item == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(item, HttpStatus.CREATED);
	}
	
	/**
	 * Update item.
	 * 
	 * @param item
	 * @return Item object (updated item object)
	 */
	@RequestMapping(value="/{itemId}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> updateItem(@RequestBody Item item){
		
		item = itemService.update(item);
		if (item == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

}
