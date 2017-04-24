package com.tragent.pressing.model;

import java.util.Date;

public class PaymentDTO {

	private Long customerItemId;
	private Long paymentMethodId;
	private Date paymentDate;
	private Double amount;
	
	public PaymentDTO() {
		super();
	}

	public PaymentDTO(Long customerItemId, Long paymentMethodId, Double amount, Date paymentDate) {
		super();
		this.customerItemId = customerItemId;
		this.paymentMethodId = paymentMethodId;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}

	public Long getCustomerItemId() {
		return this.customerItemId;
	}

	public void setCustomerItemId(Long customerItemId) {
		this.customerItemId = customerItemId;
	}

	public Long getPaymentMethodId() {
		return this.paymentMethodId;
	}

	public void setPaymentMethodId(Long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}	
	
}
