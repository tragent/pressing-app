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

import com.tragent.pressing.model.Customer;
import com.tragent.pressing.service.CustomerService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("api/v1/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	/**
	 * Get all customers or collection of active customer or customer with a given email.
	 * 
	 * @param customerEmail, isActive
	 * @return Collection of customers or customer with the particular email
	 */
	@RequestMapping(method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Customer>> getCustomers(@RequestParam(value = "customerEmail", required = false) String customerEmail,
			@RequestParam(value = "active", required = false) String isActive){
		
		Collection<Customer> customers = new ArrayList<>();
		if (customerEmail != null) {
			Customer customer = customerService.findByEmail(customerEmail);
			customers.add(customer);
			
		} else if (isActive != null) {
			if (isActive.compareToIgnoreCase("true") == 0){
				Collection<Customer> activeCustomers = customerService.findByIsActive(true);
				customers.addAll(activeCustomers);
				
			} else if(isActive.compareToIgnoreCase("false") == 0) {
				Collection<Customer> deactivatedCustomers = customerService.findByIsActive(false);
				customers.addAll(deactivatedCustomers);
				
			}
		} else {
			Collection<Customer> allCustomers = customerService.findAll();
			customers.addAll(allCustomers);
		}
		
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	/**
	 * Get customer with given customer id.
	 * 
	 * @param customerId
	 * @return Customer object or 404 if customer is not found
	 */
	@RequestMapping(value="/{customerId}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long customerId){
		
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	/**
	 * Create new customer.
	 * 
	 * @param customer
	 * @return Customer object (created object)
	 */
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
		
		customer = customerService.create(customer);
		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
	
	/**
	 * Update customer.
	 * 
	 * @param customer
	 * @return Customer object (updated object)
	 */
	@RequestMapping(value="/{customerId}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
		
		customer = customerService.update(customer);
		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	/**
	 * Deactivate a customer's account
	 * 
	 * @param customerId
	 * @return HTTP status, NON_CONTENT
	 */
	@RequestMapping(value="/{customerId}",
			method=RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> deActivateCustomer(@PathVariable("customerId") Long customerId){
		
		customerService.deactivate(customerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
