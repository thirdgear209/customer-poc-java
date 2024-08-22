package com.matthewdalby.example.customer.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matthewdalby.example.customer.entity.AdditionalInfo;
import com.matthewdalby.example.customer.entity.Address;
import com.matthewdalby.example.customer.entity.Customer;
import com.matthewdalby.example.customer.entity.EmailAddress;
import com.matthewdalby.example.customer.entity.Note;
import com.matthewdalby.example.customer.entity.PhoneNumber;
import com.matthewdalby.example.customer.exception.EntityNotFoundException;
import com.matthewdalby.example.customer.exception.InvalidRequestException;
import com.matthewdalby.example.customer.repo.CustomerRepo;
import com.matthewdalby.example.customer.repo.PhoneNumberRepo;
import com.matthewdalby.example.customer.service.CustomerIDGeneratonService;
import com.matthewdalby.example.customer.service.CustomerService;
import com.matthewdalby.example.customer.service.CustomerValidationService;
import com.matthewdalby.example.customer.vo.CustomerRefVo;

import jakarta.validation.Validator;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private PhoneNumberRepo phoneNumberRepo;
	
	@Autowired
	private CustomerValidationService vaidationService;
	
	@Autowired
	private CustomerIDGeneratonService idService;
	
	public CustomerRefVo getCustomerSummaryById(Long id) {
		CustomerRefVo vo;
		System.out.println("searching for customer by pk: "+ id);
		
		Customer customer = getById(id);
		if( customer==null ) {
			throw new EntityNotFoundException("Cannot find customer with id "+ id);
		}
		
		/**
		 * Here we make an assumption that at a minimum a phone number must be associated with a customer 
		 * record. There is no assumption that a valid record will be in this state. 
		 */
		
		vo = convertToVo(customer);
		
		return vo;
	}
	
	
	public Customer createCustomer(Customer customer) {
		
		vaidationService.validateCustomer(customer);
		
		customer.setCustomerId(idService.getNewCustomerId());
		
		List<PhoneNumber> phoneNumbers = customer.getPhoneNumbers();
		for( PhoneNumber phoneNumber: phoneNumbers) {
			phoneNumber.setCustomer(customer);
		}
		
		List<Address>addresses = customer.getAddresses();
		if(addresses!=null) {
			for(Address address : addresses) {
				address.setCustomer(customer);	
			}
		}
		
		List<EmailAddress>emails = customer.getEmailAddresses();
		if(emails!=null) {
			for(EmailAddress email : emails) {
				email.setCustomer(customer);
			}
		}
		
		List<Note>notes = customer.getNotes();
		if(notes!=null) {
			for(Note note : notes) {
				note.setCustomer(customer);
			}
		}
		
		List<AdditionalInfo>additionalInfo = customer.getAdditionalInfo();
		if(additionalInfo!=null) {
			for(AdditionalInfo info : additionalInfo) {
				info.setCustomer(customer);
			}
		}
		
		
		return customerRepo.save(customer);
	}
	
	
	public Customer getById(Long id) {
		Optional<Customer> customerRef = customerRepo.findById(id);
		if(!customerRef.isPresent()) {
			return null;
		}
		Customer customer = customerRef.get();
		return customer;
	}
	
	public boolean doesCustomerExist(String customerId) {
		return getCustomerPkByCustomerId(customerId) == null ? false : true; 
	}
	
	/**
	 * 
	 * @param customerId
	 */
	public Long getCustomerPkByCustomerId(String customerId) {
		return customerRepo.getCustomerIdByCustomerNumber(customerId);
	}


	CustomerRefVo convertToVo(Customer customer) {
		return new CustomerRefVo(customer.getId(), customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getCreatedAt(), customer.getUpdatedAt(), customer.getPrimaryPhoneNumber().getFormattedNumber());
	}
	
	@Override
	public List<CustomerRefVo> searchByPhoneNumber(String phoneNumber) {
		return null;
	}
	
	public List<CustomerRefVo>browse(){
		Iterable<Customer> allCustomers = customerRepo.findAll();
		List<CustomerRefVo> results = new ArrayList();
		
		for(Customer customer: allCustomers) {
			results.add(convertToVo(customer));
		}
			
		return results;
	}
	
	/*
	*/
	
	/*
	@Override
	public Customer getByCustomerId(String id) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	/*
	public CustomerRefVo findByPhoneNumber(String phoneNumber) {
		return null;
	}*/

	/*
	@Override
	public List<CustomerRefVo> search(String firstName, String lastName, String phoneNumber, String city, String customerId) {
		
		if(phoneNumber!=null) {
			String customerId = repo.findByPhoneNumber(phoneNumber);
			if(customerId == null ) {
				return null;
			}
			Optional<Customer> customer = repo.findById(customerId);
			// return customer;
			return null;
		}
		
		if(firstName==null || lastName==null || city==null) {
			throw new InvalidRequestException("Either phone number criteria must be present, or values for first, "
					+ "last, and city must be provided.");
		}
		
		return null;
	}*/

	/*
	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		
	}*/

	/*
	@Override
	public void deleteCustomer(Long id) {
		// TODO Auto-generated method stub
		
	}*/
	
	
}
