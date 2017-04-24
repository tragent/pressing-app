package com.tragent.pressing.service;

import java.util.Collection;

import com.tragent.pressing.model.PaymentMethod;


/**
 * Service that provides CRUD operations for payment methods
 **/
public interface PaymentMethodService {

	/**
	 * Get all payment methods in the system.
	 * 
	 * @return Collection of payment methods
	 */
	public Collection<PaymentMethod> findAll();
	
	/**
	 * Find a payment method by Id.
	 * 
	 * @param id
	 * @return PaymentMethod object if found, else return null
	 */
	public PaymentMethod findById(Long id);
	
	/**
	 * Find a payment method by name.
	 * 
	 * @param name
	 * @return Payment method object if found, else return null
	 */
	public PaymentMethod findByName(String name);
	
	/**
	 * Find active / deactivated payment methods.
	 * 
	 * @param isActive
	 * @return Collection PaymentMethod object
	 */
	public Collection<PaymentMethod> findByIsActive(boolean isActive);
	
	/**
	 * Create new payment method.
	 * 
	 * @param paymentMethod
	 * @return PaymentMethod object (Created PaymentMethod object)
	 */
	public PaymentMethod create(PaymentMethod paymentMethod);
	
	/**
	 * Update an existing payment method's information.
	 * 
	 * @param paymentMethod
	 * @return PaymentMethod object (Updated PaymentMethod object)
	 */
	public PaymentMethod update(PaymentMethod paymentMethod);
	
	/**
	 * Deactivate a payment method .
	 * 
	 * @param id
	 */
	public void deactivate(Long id);
	
	
}
