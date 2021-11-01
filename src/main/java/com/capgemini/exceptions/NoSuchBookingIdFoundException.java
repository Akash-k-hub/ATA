package com.capgemini.exceptions;

public class NoSuchBookingIdFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoSuchBookingIdFoundException(String message)
	{
		super(message);
	}
}
