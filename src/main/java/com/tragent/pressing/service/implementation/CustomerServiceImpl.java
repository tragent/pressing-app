package com.tragent.pressing.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tragent.pressing.model.Customer;
import com.tragent.pressing.repository.CustomerRepository;
import com.tragent.pressing.service.CustomerService;

@Service
@Secured("ROLE_MANAGEMENT")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Collection<Customer> findAll() {
		
		Collection<Customer> customers = customerRepository.findAll();
		return customers;
	}

	@Override
	public Customer findById(Long id) {
		

		Customer customer = customerRepository.findOne(id);
		return customer;
	}

	@Override
	public Customer findByEmail(String email) {
		
		Customer customer = customerRepository.findByEmail(email);
		return customer;
	}

	@Override
	public Collection<Customer> findByIsActive(boolean isActive) {
		
		Collection<Customer> customers = customerRepository.findByIsActive(isActive);
		return customers;
	}

	@Override
	public Customer create(Customer customer) {
		
		if (findByEmail(customer.getEmail()) == null) {
			Customer savedCustomer = customerRepository.save(customer);
			return savedCustomer;
		}
		
		return null;
	}

	@Override
	public Customer update(Customer customer) {

		Customer savedCustomer = customerRepository.save(customer);
		return savedCustomer;
		
	}

	@Override
	public void deactivate(Long id) {
		
		Customer customer = findById(id);
		if (customer == null){
			return;
		}
		
		customer.setActive(false);
	}

}
