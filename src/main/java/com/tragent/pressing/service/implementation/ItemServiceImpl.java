package com.tragent.pressing.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tragent.pressing.model.Category;
import com.tragent.pressing.model.Item;
import com.tragent.pressing.repository.CategoryRepository;
import com.tragent.pressing.repository.ItemRepository;
import com.tragent.pressing.service.ItemService;

@Service
@Secured("ROLE_MANAGEMENT")
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Collection<Item> findAll() {
		
		Collection<Item> items = itemRepository.findAll();
		return items;	
	}

	@Override
	public Item findById(Long id) {
		
		Item item = itemRepository.findOne(id);
		return item;
	}

	@Override
	public Item findByName(String name) {
		
		Item item = itemRepository.findByName(name);
		return item;
	}

	@Override
	public Item create(Item item) {
		
		if (itemRepository.findByName(item.getName()) == null) {
			Item savedItem = itemRepository.save(item);
			return savedItem;
		}
		
		return null;
	}

	@Override
	public Item update(Item item) {

		Item updatedItem = itemRepository.save(item);
		return updatedItem;
	}

	@Override
	public void delete(Long id) {
		
		Item item = findById(id);
		if (item == null) {
			return;
		}
		
		itemRepository.delete(item);
	}

	@Override
	public Collection<Item> findCategoryItems(Long categoryId) {
		
		Category category = categoryRepository.findOne(categoryId);
		Collection<Item> items = itemRepository.findByCategory(category);
		return items;
	}

}
