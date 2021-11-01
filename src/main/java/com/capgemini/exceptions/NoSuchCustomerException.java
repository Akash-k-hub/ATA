package com.capgemini.exceptions;

public class NoSuchCustomerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoSuchCustomerException() {}
	
	public NoSuchCustomerException(String s) {
		super(s);
	}
}
