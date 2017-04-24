package com.tragent.pressing.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.omg.CORBA.ServerRequest;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="customer_item")
public class Transaction implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ServerRequest.class)
	@ManyToOne(optional=false)
	@JoinColumn(name="customer_id", referencedColumnName = "id")
	private Customer customer;
		
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ServerRequest.class)
	@ManyToOne(optional=false)
	@JoinColumn(name="item_id", referencedColumnName = "id")
	private Item item;
	
	@Column(nullable=false)
	private int quantity;
	
	@Column(nullable=false)
	private String status;
	
	@Column(nullable=false, unique=true)
	private String label;
	
	@Column(nullable=false)
	private Date depositDate = new Date();
	
	@Column(nullable=false)
	private Date dueDate;
	
	@Column(nullable=false)
	@OneToMany(mappedBy="customerItem", cascade=CascadeType.ALL)
	private List<Payment> payments;

	public Transaction() {
		super();
	}

	public Transaction(Customer customer, Item item, int quantity, String status, String label, Date dueDate, Date depositDate) {
		super();
		this.customer = customer;
		this.item = item;
		this.quantity = quantity;
		this.status = status;
		this.label = label;
		this.dueDate = dueDate;
		this.depositDate = depositDate;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getDepositDate() {
		return this.depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
}
