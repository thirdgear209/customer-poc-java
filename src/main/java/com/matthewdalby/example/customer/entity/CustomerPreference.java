package com.matthewdalby.example.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CustomerPreference {

	@Id
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
	
	private CustomerPreferenceTypeEnum customerPreferenceType;
	
	private boolean enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerPreferenceTypeEnum getCustomerPreferenceType() {
		return customerPreferenceType;
	}

	public void setCustomerPreferenceType(CustomerPreferenceTypeEnum customerPreferenceType) {
		this.customerPreferenceType = customerPreferenceType;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	} 
	
}
