package com.tragent.pressing.service.implementation;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tragent.pressing.model.Transaction;
import com.tragent.pressing.model.Payment;
import com.tragent.pressing.model.PaymentMethod;
import com.tragent.pressing.repository.PaymentRepository;
import com.tragent.pressing.service.TransactionService;
import com.tragent.pressing.service.PaymentMethodService;
import com.tragent.pressing.service.PaymentService;

@Service
@Secured({"ROLE_MANAGEMENT", "ROLE_SALES_AGENT", "ROLE_ADMINISTRATION"})
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private PaymentMethodService paymentMethodService;
	
	@Override
	public Collection<Payment> findAll() {
		
		Collection<Payment> payments = paymentRepository.findAll();
		return payments;
	}

	@Override
	public Collection<Payment> findPaymentByTransaction(Long transactionId) {
		
		Transaction transaction = transactionService.findById(transactionId);
		Collection<Payment> payments = paymentRepository.findByCustomerItem(transaction);
		return payments;
	}

	@Override
	public Collection<Payment> findByPaymentByTime(Date time) {
		
		Collection<Payment> payments = paymentRepository.findByTime(time);
		return payments;
	}

	@Override
	public Collection<Payment> findPaymentWithPaymentMethod(Long methodId) {

		PaymentMethod method = paymentMethodService.findById(methodId);
		Collection<Payment> payments = paymentRepository.findByPaymentMethod(method);
		System.out.println("Checking error");
		return payments;
	}

	@Override
	public Payment create(Payment payment) {
		
		Payment savedPayment = paymentRepository.save(payment);
 		return savedPayment;
	}

	@Override
	public Payment update(Payment payment) {
		
		Payment updatedPayment = paymentRepository.save(payment);
		return updatedPayment;
	}

}
