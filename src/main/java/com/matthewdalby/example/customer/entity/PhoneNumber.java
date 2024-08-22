package com.matthewdalby.example.customer.entity;

import java.sql.Timestamp;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;

@Entity
public class PhoneNumber {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private int areaCode;
	
	@NotNull
	private int number;
	
	private Timestamp createdAt;
	
	private Timestamp updatedAt;
	
	private boolean isPrimary = true;
	
	// @Column(name="active")
	private boolean isActive = true;
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
	
	@NotNull(message = "A phone number must have a type defined.")
	private PhoneNumberTypeEnum type;
	
	public PhoneNumberTypeEnum getType() {
		return type;
	}

	public void setType(PhoneNumberTypeEnum type) {
		this.type = type;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	@PrePersist
	protected void onCreate() {
	    createdAt = Timestamp.from(Instant.now());
	}
	
	@PreUpdate
	protected void onUpdate() {
	    updatedAt = Timestamp.from(Instant.now());
	}
	
	public String splitNumber (int val) {
		String numberStr = Integer.toString(val);
		return numberStr.substring(0, 3) +"-"+ numberStr.substring(3,7);
	}
	
	public String getFormattedNumber() {
		return String.format("(%s)%s", this.areaCode, splitNumber(this.number));
	}

	
}
