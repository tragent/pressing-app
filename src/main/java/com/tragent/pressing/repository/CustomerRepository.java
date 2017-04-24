package com.tragent.pressing.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tragent.pressing.model.Customer;

public interface CustomerRepository  extends JpaRepository<Customer, Long>  {

	Customer findByEmail(@Param("email") String email);
	Collection<Customer> findByIsActive(@Param("isActive") boolean isActive);
	
}
