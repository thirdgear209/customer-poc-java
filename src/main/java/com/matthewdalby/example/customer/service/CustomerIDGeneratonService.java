package com.matthewdalby.example.customer.service;

/** 
 * CustomerIDGeneratonService.java
 * 
 * Centralized generation of customer IDs. The implementation is not really 
 * important here as this is a pluggable solution.
 * 
 */
public interface CustomerIDGeneratonService {

	public String getNewCustomerId();
}
