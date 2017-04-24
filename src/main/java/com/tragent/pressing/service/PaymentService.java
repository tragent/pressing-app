package com.tragent.pressing.service;

import java.util.Collection;
import java.util.Date;

import com.tragent.pressing.model.Payment;

/**
 * Service that provides CRUD operations for payments
 **/
public interface PaymentService {

	/**
	 * Get all Payments made in the system.
	 * 
	 * @return Collection of payments
	 */
	public Collection<Payment> findAll();
	
	/**
	 * Get payments with same payment method
	 * 
	 * @param paymentId
	 * @return Collection of payments
	 */
	public Collection<Payment> findPaymentWithPaymentMethod(Long paymentId);
	
	/**
	 * Get payment made by a customer for an item.
	 * 
	 * @param cutomerItemId
	 * @return Collection of payments
	 */
	public Collection<Payment> findPaymentByTransaction(Long transactionId);
	
	/**
	 * Get payment made on a particular time.
	 * 
	 * @param time, 
	 * @return Collection of payments
	 */
	public Collection<Payment> findByPaymentByTime(Date time);
	
	/**
	 * Create payments made by a customer for an item.
	 * 
	 * @param payment
	 * @return Payment object(created payment object)
	 */
	public Payment create(Payment payment);
	
	/**
	 * Update an existing Customer Item transaction's information.
	 * 
	 * @param payment
	 * @return Payment object (updated payment object)
	 */
	public Payment update(Payment payment);
	
}
