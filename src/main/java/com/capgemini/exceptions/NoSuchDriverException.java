package com.capgemini.exceptions;

public class NoSuchDriverException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoSuchDriverException() {
		super();
	}

	public NoSuchDriverException(String s) {
		super(s);
	}
}
