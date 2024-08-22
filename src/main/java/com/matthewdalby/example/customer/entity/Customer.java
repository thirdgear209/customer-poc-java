package com.matthewdalby.example.customer.entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;

// import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Customer.java
 * 
 * 		Top level entity in the customer graph.
 * 
 * 		Customers must have at least an address and a phone number defined. Also
 * 
 */
@Entity
public class Customer{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** Unique public facing identifier. */
	private String customerId;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String firstName;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String lastName;
	
	@OneToMany(mappedBy="customer")
	private List<Address> addresses = new ArrayList();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="customer")
	private List<PhoneNumber>phoneNumbers = new ArrayList();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="customer")
	private List<EmailAddress>emailAddresses = new ArrayList();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="customer")
	private List<Note>notes = new ArrayList();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="customer")
	private List<CardTransaction>transactions = new ArrayList();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="customer")
	private List<AdditionalInfo> additionalInfo = new ArrayList();
	
	private Timestamp createdAt;
	public List<AdditionalInfo> getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(List<AdditionalInfo> additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	private Timestamp updatedAt;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(List<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<CardTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<CardTransaction> transactions) {
		this.transactions = transactions;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@PrePersist
	protected void onCreate() {
	    createdAt = Timestamp.from(Instant.now());
	}
	
	@PreUpdate
	protected void onUpdate() {
	    updatedAt = Timestamp.from(Instant.now());
	}
	
	public PhoneNumber getPrimaryPhoneNumber() {
		
		for(PhoneNumber phoneNumber : this.getPhoneNumbers()) {
			if(phoneNumber.isPrimary()) {
				return phoneNumber;
			}
		}
		
		return null;
	}
	
}