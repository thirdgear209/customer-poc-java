package com.matthewdalby.example.customer.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.matthewdalby.example.customer.entity.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Long>{

	
	@Query(nativeQuery=true, value="select customer.id from PhoneNumber pn where pn.number = :phoneNumber and pn.areaCode=:areaCode")
	public Long findByPhoneNumbers(int phoneNumber, int areaCode); 

	@Query(nativeQuery=true, value="select customer.id from Customer customer where customer.customer_number = :customerNumber")
	public Long getCustomerIdByCustomerNumber(String customerNumber);
	
	
}
