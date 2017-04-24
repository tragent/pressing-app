package com.tragent.pressing.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

import com.tragent.pressing.model.CustomerItem;
import com.tragent.pressing.model.Payment;
import com.tragent.pressing.model.PaymentDTO;
import com.tragent.pressing.model.Transaction;
import com.tragent.pressing.service.PaymentMethodService;
import com.tragent.pressing.service.PaymentService;
import com.tragent.pressing.service.TransactionService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("api/v1/customertransactions")
public class CustomerTransactionController {
		
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private PaymentMethodService paymentMethodService;
		
	/**
	 * Get all transactions or a customer or item transaction with a given id.
	 * 
	 * @param depositDate, dueDate, customerId, itemId
	 * @return Collection of transactions or a customer's transactions with the given id.
	 * @throws ParseException 
	 */
	@RequestMapping(method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Transaction>> getTransactions(@RequestParam(value = "depositDate", required = false) String depositDate,
			@RequestParam(value = "dueDate", required = false) String dueDate, @RequestParam(value = "customerId", required = false) Long customerId, 
			@RequestParam(value = "itemId", required = false) Long itemId, @RequestParam(value = "greatestDueDate", required = false) String greatestDueDate,
			@RequestParam(value = "status", required = false) String status)
					throws ParseException {
		
		Collection<Transaction> transactions = new ArrayList<>();
		if (depositDate != null) {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = formatter.parse(depositDate);
			Collection<Transaction> transaction = transactionService.findByDepositeDate(date);
			transactions.addAll(transaction);
			
		} else if (dueDate != null) {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = formatter.parse(dueDate);
			Collection<Transaction> transaction = transactionService.findByDueDate(date);
			transactions.addAll(transaction);
			
		} else if (greatestDueDate != null) {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = formatter.parse(greatestDueDate);
			Collection<Transaction> transaction = transactionService.transactionsPastDueDate(date);
			transactions.addAll(transaction);
			
		} else if (customerId != null) {
			Collection<Transaction> transaction = transactionService.findByCustomerId(customerId);
			transactions.addAll(transaction);
			
		} else if (itemId != null) {
			Collection<Transaction> transaction = transactionService.findByItemId(itemId);
			transactions.addAll(transaction);
						
		} else if (status != null) {
			if(status.equalsIgnoreCase("clean")){
				List<String> statusList = new ArrayList<>();
				statusList.add("READY_AND_NOT_PAID");
				statusList.add("READY_AND_PAID");
				statusList.add("COLLECTED");
				Collection<Transaction> transaction = transactionService.findByStatus(statusList);
				transactions.addAll(transaction);
			} else if(status.equalsIgnoreCase("dirty")){
				List<String> statusList = new ArrayList<>();
				statusList.add("PENDING");
				statusList.add("WASHING");
				Collection<Transaction> transaction = transactionService.findByStatus(statusList);
				transactions.addAll(transaction);
			}
						
		} else {
			Collection<Transaction> allTransactions = transactionService.findAll();
			transactions.addAll(allTransactions);
		}
		
		return new ResponseEntity<>(transactions, HttpStatus.OK);
		
	}
	
	/**
	 * Get transaction with given transaction id.
	 * 
	 * @param transactionId
	 * @return Transaction object or 404 if transaction is not found
	 */
	@RequestMapping(value="/{transactionId}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> getTransactionById(@PathVariable("transactionId") Long transactionId){
		
		Transaction transaction = transactionService.findById(transactionId);
		if (transaction == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}
	
	/**
	 * Get payments made on transaction with given id.
	 * 
	 * @param transactionId
	 * @return collection of payments for a transaction or 404 if transactions is not found
	 */
	@RequestMapping(value="/{transactionId}/payments",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Payment>> getTransactionPayments(@PathVariable("transactionId") Long transactionId){
		
		Collection<Payment> payments = transactionService.findById(transactionId).getPayments();
		if (payments == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(payments, HttpStatus.OK);
	}
	
	/**
	 * Create new transaction record.
	 * 
	 * @param customerItem
	 * @return Transaction object (created Transaction object)
	 */
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> createTransaction(@RequestBody CustomerItem customerItem){
		
		Transaction transaction = new Transaction(customerItem.getCustomer(), customerItem.getItem(),
				customerItem.getQuantity(), customerItem.getStatus(), customerItem.getLabel(),
				customerItem.getDueDate(),customerItem.getDepositDate());
		transaction = transactionService.create(transaction);
		if (transaction == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(transaction, HttpStatus.CREATED);	
	}
	
	/**
	 * Update customer item transaction object.
	 * 
	 * @param customerItem
	 * @return Transaction object (updated CustomerItem object).
	 */
	@RequestMapping(value="/{transactionId}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> updateCustomerTransaction(@RequestBody CustomerItem customerItem){
		
		Transaction transaction = transactionService.findById(customerItem.getId());
		transaction.setQuantity(customerItem.getQuantity());
		transaction.setStatus(customerItem.getStatus());
		transaction = transactionService.update(transaction);
		if (transaction == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(transaction, HttpStatus.OK);
		
	}
	
	/**
	 * Get Add payment to customer transaction.
	 * 
	 * @param payment
	 * @return corresponding Transaction object
	 */
	@RequestMapping(value="/{transactionId}/payments",
			method=RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Payment> depositePayment(@RequestBody PaymentDTO paymentDTO){
		Payment payment = new Payment(paymentDTO.getAmount(), paymentDTO.getPaymentDate(), 
				transactionService.findById(paymentDTO.getCustomerItemId()),paymentMethodService.findById(paymentDTO.getPaymentMethodId()));
		Payment createdPayment = paymentService.create(payment);
		if (createdPayment == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(createdPayment, HttpStatus.OK);
	}
}
