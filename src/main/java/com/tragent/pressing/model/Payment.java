package com.tragent.pressing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.omg.CORBA.ServerRequest;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="payment")
public class Payment {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private double amount;
	
	@Column(nullable=false)
	private Date time = new Date();
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ServerRequest.class)
	@ManyToOne(optional=false)
	@JoinColumn(name="customer_item_id", referencedColumnName="id")
	private Transaction customerItem;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ServerRequest.class)
	@ManyToOne(optional=false)
	@JoinColumn(name="payment_method_id", referencedColumnName = "id")
	private PaymentMethod paymentMethod;

	public Payment() {
		super();
	}

	public Payment(double amount, Date time, Transaction customerItem, PaymentMethod paymentMethod) {
		super();
		this.amount = amount;
		this.time = time;
		this.customerItem = customerItem;
		this.paymentMethod = paymentMethod;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Transaction getCustomerItem() {
		return this.customerItem;
	}

	public void setCustomerItem(Transaction customerItem) {
		this.customerItem = customerItem;
	}

	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
}
