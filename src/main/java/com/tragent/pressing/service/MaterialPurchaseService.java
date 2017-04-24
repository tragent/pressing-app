package com.tragent.pressing.service;

import java.util.Collection;
import java.util.Date;

import com.tragent.pressing.model.MaterialPurchase;

/**
 * Service that provides CRUD operations for material purchases
 **/
public interface MaterialPurchaseService {
	
	/**
	 * Get all cleaning material purchases in the system.
	 * 
	 * @return Collection of all material purchases existing in the system
	 */
	public Collection<MaterialPurchase> findAll();
	
	/**
	 * Find a material purchase by Id.
	 * 
	 * @param purchaseId
	 * @return MaterialPurchase object if found, else return null
	 */
	public MaterialPurchase findById(Long id);
	
	/**
	 * Find a purchase by purchase date.
	 * 
	 * @param purchasedDate
	 * @return Collection of purchases
	 */
	public Collection<MaterialPurchase> findByPurchasDate(Date purchaseDate);
	

	/**
	 * Find a purchase by cleaning material.
	 * 
	 * @param materialId
	 * @return Collection of purchases
	 */
	public Collection<MaterialPurchase> findByCleaningMaterial(Long materialId);
	
	/**
	 * Create a new purchase.
	 * 
	 * @param purchase
	 * @return MatetrialPurchase object (created purchase transaction) 
	 */
	public MaterialPurchase create(MaterialPurchase purchase);
	
	/**
	 * Update an existing purchase.
	 * 
	 * @param purchase the updated purchase's record
	 * @return MatetrialPurchase object (updated purchase transaction)
	 */
	public MaterialPurchase update(MaterialPurchase purchase);
	
}
