package com.matthewdalby.example.customer.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matthewdalby.example.customer.entity.Customer;
import com.matthewdalby.example.customer.exception.InvalidRequestException;
import com.matthewdalby.example.customer.entity.CardTransaction;
import com.matthewdalby.example.customer.service.CustomerService;
import com.matthewdalby.example.customer.vo.CustomerRefVo;

import jakarta.validation.Valid;

@RestController
@RequestMapping("customer")
public class CustomerApi {

	@Autowired 
	private CustomerService customerService;
	
	/**
	 * Retrieves basic customer information, transactions, sensitive data, 
	 * are omitted, and only 'active' contact fields are returned. 
	 * 
	 * @param id
	 * @return
	 */
	
	@GetMapping("/{id}")
	public CustomerRefVo getCustomerSummaryById(@PathVariable Long id) {
		CustomerRefVo vo = customerService.getCustomerSummaryById(id);
		System.out.println("vo");
		System.out.println(vo);
		
		
		return customerService.getCustomerSummaryById(id);
	}

	@PostMapping
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
	
	
	/**
	 * Perform a customer search operation. 
	 * 
	 * Either first name, last name, and city needs to be provided, and/or a phone number.
	 * 
	 * Summary information, basically a subset of customer data is returned from the operation. 
	 * 
	 * Given the potential for a larger number of results, pagination is applied.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @return
	 */
	@GetMapping("/search")
	public List<CustomerRefVo>search(@RequestParam(required=false)String firstName, 
			@RequestParam(required=false)String lastName, 
			@RequestParam(required=false)String phoneNumber, 
			@RequestParam(required=false)String city){
		return null;
	}
	
	@GetMapping("/browse")
	public List<CustomerRefVo>browse(){
		return customerService.browse();
	}
	
	
	
	
}
