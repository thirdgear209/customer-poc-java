package com.matthewdalby.example.customer.exception;

public class InvalidRequestException extends RuntimeException{

	public InvalidRequestException(String msg) {
		super(msg);
	}
}
