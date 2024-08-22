package com.matthewdalby.example.customer.service;

import java.util.List;

import com.matthewdalby.example.customer.entity.Customer;
import com.matthewdalby.example.customer.vo.CustomerRefVo;

public interface CustomerService {

	public CustomerRefVo getCustomerSummaryById(Long customerId);
	
	public Customer createCustomer(Customer customer);
	
	public List<CustomerRefVo>searchByPhoneNumber(String phoneNumber);
	
	public List<CustomerRefVo>browse();
	
	/*
	public Customer getById(Long id);
	*/
	
	/*
	public Customer getByCustomerId(String id);*/
	
	/*
	
	public List<CustomerRefVo>search(String firstName, String lastName, String phoneNumber, String city, String customerId);
	*/
	
	/*
	public void update(Customer customer);*/
	
	/*
	
	public void deleteCustomer(Long id); */
	
	/**
	 * 
	 * @param phoneNumber
	 * @return
	 */
	/*
	public List<CustomerRefVo> searchByPhoneNumber(String phoneNumber);
	*/
}
