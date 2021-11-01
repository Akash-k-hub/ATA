package com.capgemini.exceptions;

public class NoSuchAddressException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoSuchAddressException() {
		super();
	}

	public NoSuchAddressException(String s) {
		super(s);
	}

}
