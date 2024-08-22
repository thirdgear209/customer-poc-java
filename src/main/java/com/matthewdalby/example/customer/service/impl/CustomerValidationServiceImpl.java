package com.matthewdalby.example.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.matthewdalby.example.customer.entity.Customer;
import com.matthewdalby.example.customer.entity.PhoneNumber;
import com.matthewdalby.example.customer.exception.InvalidRequestException;
import com.matthewdalby.example.customer.repo.CustomerRepo;

import com.matthewdalby.example.customer.service.CustomerValidationService;

@Service("CustomerValidationService")
public class CustomerValidationServiceImpl implements CustomerValidationService{
	
	@Autowired
	CustomerRepo customerRepo;

	public void validateCustomer(Customer customer) {
		
		// At least a single phone number exists
		if( customer.getPhoneNumbers().size()<1) {
			throw new InvalidRequestException("At least a single phone number should be defined.");
		}
		
		// ...And at least a primary.
		if(customer.getPrimaryPhoneNumber()==null) {
			throw new InvalidRequestException("A phone number should be defined.");
		}
		
	}
	
	
}
