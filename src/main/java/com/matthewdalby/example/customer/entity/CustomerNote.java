package com.matthewdalby.example.customer.entity;

import java.sql.Timestamp;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Size;

/**
 * CustomerNote.java
 * 
 * Notes are created and associated with the customer entity. Notes are stored for
 * historical purposes.
 * 
 */
@Entity
public class CustomerNote {
	
	@Id
	private long id;
	
	@Size(min = 5, max = 255)
	private String notes; 
	
	/**
	 * 
	 */
	private Timestamp createdAt;
	
	private Timestamp updatedAt;
	
	@PrePersist
	protected void onCreate() {
	    createdAt = Timestamp.from(Instant.now());
	}
	
	@PreUpdate
	protected void onUpdate() {
	    updatedAt = Timestamp.from(Instant.now());
	}
}
