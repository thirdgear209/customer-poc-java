package com.matthewdalby.example.customer.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.matthewdalby.example.customer.service.CustomerIDGeneratonService;

@Service("CustomerIDGeneratonService")
public class CustomerIDGeneratonServiceImpl implements CustomerIDGeneratonService{

	@Override
	public String getNewCustomerId() {
		
		LocalDateTime now = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

		return now.format(formatter);
	}

}
