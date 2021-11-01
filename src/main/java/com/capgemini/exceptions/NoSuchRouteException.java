package com.capgemini.exceptions;

public class NoSuchRouteException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoSuchRouteException() {
		super();
	}

	public NoSuchRouteException(String s) {
		super(s);
	}
}
