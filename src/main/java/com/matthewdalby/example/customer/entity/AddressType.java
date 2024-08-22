package com.matthewdalby.example.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AddressType {
	
	@Id
	private String type;
	
	private String id;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
