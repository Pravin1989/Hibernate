package com.pravin.manytoone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "table_items1")
public class Items {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "item_id")
	private String itemId;

	@Column(name = "item_total")
	private double itemTotal;

	@Column(name = "quantity")
	private int quantity;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public double getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(double itemTotal) {
		this.itemTotal = itemTotal;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	// Hibernate requires no-args constructor
	public Items() {
	}

	public Items(String itemId, double total, int qty, Cart c) {
		this.itemId = itemId;
		this.itemTotal = total;
		this.quantity = qty;
		this.cart = c;
	}
}
