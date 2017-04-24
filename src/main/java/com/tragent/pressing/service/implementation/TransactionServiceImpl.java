package com.tragent.pressing.service.implementation;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tragent.pressing.model.Transaction;
import com.tragent.pressing.repository.TransactionRepository;
import com.tragent.pressing.service.TransactionService;

@Service
@Secured({"ROLE_MANAGEMENT", "ROLE_SALES_AGENT"})
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Collection<Transaction> findAll() {
		
		Collection<Transaction> transactions = transactionRepository.findAll();
		return transactions;
	}

	@Override
	public Transaction findById(Long transactionId) {
		
		Transaction transaction = transactionRepository.findOne(transactionId);
		return transaction;
	}

	@Override
	public Collection<Transaction> findByCustomerId(Long customerId) {
		
		Collection<Transaction> transaction = transactionRepository.findByCustomer(customerId);
		return transaction;
	}

	@Override
	public Collection<Transaction> findByItemId(Long itemId) {
		
		Collection<Transaction> transaction = transactionRepository.findByItem(itemId);
		return transaction;
	}

	@Override
	public Transaction create(Transaction transaction) {
		Transaction savedCustomerItem = transactionRepository.save(transaction);
		return savedCustomerItem;
	}

	@Override
	public Transaction update(Transaction transaction) {
		
		Transaction updatedTransaction = transactionRepository.save(transaction);
		return updatedTransaction;
	}

	@Override
	public Collection<Transaction> findByDepositeDate(Date depositDate) {
		
		Collection<Transaction> transaction = transactionRepository.findByDepositDate(depositDate);
		return transaction;
	}

	@Override
	public Collection<Transaction> findByDueDate(Date dueDate) {
		
		Collection<Transaction> transaction = transactionRepository.findByDueDate(dueDate);
		return transaction;
	}

	@Override
	public Collection<Transaction> transactionsPastDueDate(Date dueDate) {
		
		Collection<Transaction> transactions = transactionRepository.findByDueDateBeforeAndStatusNot(dueDate, "COLLECTED");
		return transactions;
	}

	@Override
	public Collection<Transaction> findByStatus(Collection<String> status) {
		Collection<Transaction> transactions = transactionRepository.findByStatusIn(status);
		return transactions;
	}
}
