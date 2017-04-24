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

import com.tragent.pressing.model.Payment;
import com.tragent.pressing.model.PaymentMethod;
import com.tragent.pressing.service.PaymentMethodService;
import com.tragent.pressing.service.PaymentService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("api/v1/paymentmethods")
public class PaymentMethodController {

	@Autowired
	private PaymentMethodService paymentMethodService;
	
	@Autowired
	private PaymentService paymentService;
	
	/**
	 * Get all payment method or payment method with a given name.
	 * 
	 * @param methodName, isActive
	 * @return Collection of payment methods or payment method with the given name
	 */
	@RequestMapping(method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PaymentMethod>> getPaymentMethods(@RequestParam(value = "methodName", required = false) String methodName,
			@RequestParam(value = "active", required = false) String isActive) {
		
		Collection<PaymentMethod> paymentMethods = new ArrayList<>();
		if (methodName != null) {
			PaymentMethod paymentMethod = paymentMethodService.findByName(methodName);
			paymentMethods.add(paymentMethod);
			
		} else if (isActive != null) {
			if (isActive.compareToIgnoreCase("true") == 0) {
				Collection<PaymentMethod> activePaymentMethods = paymentMethodService.findByIsActive(true);
				paymentMethods.addAll(activePaymentMethods);
			} else if (isActive.compareToIgnoreCase("false") == 0) {
				Collection<PaymentMethod> deactivatedPaymentMethods = paymentMethodService.findByIsActive(false);
				paymentMethods.addAll(deactivatedPaymentMethods);
			}
			
		} else {
			Collection<PaymentMethod> allPaymentMethod = paymentMethodService.findAll();
			paymentMethods.addAll(allPaymentMethod);
		}
		
		return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
	}
	
	/**
	 * Get payment method with a given id.
	 * 
	 * @param methodId
	 * @return PaymentMethod with the given id or 404 if payment method is not found
	 */
	@RequestMapping(value="/{methodId}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentMethod> getPaymentMethodById(@PathVariable("methodId") Long methodId){
		
		PaymentMethod paymentMethod = paymentMethodService.findById(methodId);
		if (paymentMethod == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(paymentMethod, HttpStatus.OK);
	}
	
	/**
	 * Get all payments using a given payment method( with id: methodId).
	 * 
	 * @param methodId
	 * @return Collection of payments
	 */
	@RequestMapping(value="/{methodId}/payments",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Payment>> getPaymentMethodPayments(@PathVariable("methodId") Long methodId){
		
		Collection<Payment> payments = paymentService.findPaymentWithPaymentMethod(methodId);
		if (payments == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(payments, HttpStatus.OK);
	}
	
	/**
	 * Create new payment method.
	 * 
	 * @param paymentMethod
	 * @return PaymentMethod object (created PaymentMethod object)
	 */
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentMethod> createPaymentMethod(@RequestBody PaymentMethod method){
		
		method = paymentMethodService.create(method);
		if (method == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(method, HttpStatus.CREATED);
	}
	
	/**
	 * Update payment method record.
	 * 
	 * @param paymenMethod
	 * @return PaymentMethod object (updated PaymentMethod object)
	 */
	@RequestMapping(value="/{methodId}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentMethod> updatePaymentMethod(@RequestBody PaymentMethod method){
		
		method = paymentMethodService.update(method);
		if (method == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(method, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{methodId}",
			method=RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable("methodId") Long methodId){
		
		paymentMethodService.deactivate(methodId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
