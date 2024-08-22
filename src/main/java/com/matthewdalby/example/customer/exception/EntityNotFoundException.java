package com.matthewdalby.example.customer.exception;

public class EntityNotFoundException extends RuntimeException{

	public EntityNotFoundException(String msg) {
		super(msg);
	}
	
}
