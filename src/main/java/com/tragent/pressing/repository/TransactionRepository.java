package com.tragent.pressing.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tragent.pressing.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	Collection<Transaction> findByDepositDate(@Param("date") Date date);
	Collection<Transaction> findByDueDate(@Param("date") Date date);
	Collection<Transaction> findByCustomer(@Param("id") Long id );
	Collection<Transaction> findByItem(@Param("id") Long id );
	Collection<Transaction> findByDueDateBeforeAndStatusNot(@Param("date") Date date, @Param("status") String status);
	Collection<Transaction> findByStatusIn(@Param("status") Collection<String> status );
}