package com.tragent.pressing.model;

import java.util.Date;

public class CustomerItem {

	private Long id = null;
	private Customer customer;
	private Item item;
	private int quantity;
	private String status;
	private String label;
	private Date depositDate = new Date();
	private Date dueDate;
	
	public CustomerItem() {
		super();
	}

	public CustomerItem(Customer customer, Item item, int quantity, String status, String label,
			Date depositDate, Date dueDate) {
		super();
		this.customer = customer;
		this.item = item;
		this.quantity = quantity;
		this.status = status;
		this.label = label;
		this.depositDate = depositDate;
		this.dueDate = dueDate;
	}
	
	public CustomerItem(Long id, Customer customer, Item item, int quantity, String status, String label,
			Date depositDate, Date dueDate) {
		this(customer, item, quantity, status, label, depositDate, dueDate);
		this.id = id;
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

}
