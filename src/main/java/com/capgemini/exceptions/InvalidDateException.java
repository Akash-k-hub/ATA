package com.capgemini.exceptions;

public class InvalidDateException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidDateException(String s) {
		super(s);
	}
}
