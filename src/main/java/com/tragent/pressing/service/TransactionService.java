package com.tragent.pressing.service;

import java.util.Collection;
import java.util.Date;

import com.tragent.pressing.model.Transaction;

/**
 * Service that provides CRUD operations for transactions
 **/
public interface TransactionService {
	
	/**
	 * Get all transactions in the system.
	 * 
	 * @return Collection of all transactions in the system
	 */
	public Collection<Transaction> findAll();
	
	/**
	 * Find a transaction by id.
	 * 
	 * @param id
	 * @return Transaction object if found else null
	 */
	public Transaction findById(Long transactionId);
	
	/**
	 * Find transactions made by customer with id, customerId.
	 * 
	 * @param id
	 * @return Collection of Transaction objects
	 */
	public Collection<Transaction> findByCustomerId(Long customerId);
	
	/**
	 * Find transactions with item id, itemId.
	 * 
	 * @param id
	 * @return Collection of Transaction objects
	 */
	public Collection<Transaction> findByItemId(Long itemId);
	
	/**
	 * Find transactions with status.
	 * 
	 * @param status
	 * @return Collection of Transaction objects
	 */
	public Collection<Transaction> findByStatus(Collection<String> status);
	
	/**
	 * Find transactions with deposit date, deposit date.
	 * 
	 * @param depositDate
	 * @return Collection of Transaction objects
	 */
	public Collection<Transaction> findByDepositeDate(Date depositDate);
	
	/**
	 * Find transactions with due date, dueDate.
	 * 
	 * @param dueDate
	 * @return Collection of Transaction objects
	 */
	public Collection<Transaction> findByDueDate(Date dueDate);
	
	/**
	 * Find transactions with due date less then dueDate.
	 * 
	 * @param dueDate
	 * @return Collection of Transaction objects
	 */
	public Collection<Transaction> transactionsPastDueDate(Date dueDate);
	
	/**
	 * Create a new transaction.
	 * 
	 * @param transaction
	 * @return Transaction object (created Transaction object)
	 */
	public Transaction create(Transaction transaction);
	
	/**
	 * Update an existing transaction's information.
	 * 
	 * @param transaction
	 * @return Transaction object (updated Transaction object)
	 */
	public Transaction update(Transaction transaction);
	
}
