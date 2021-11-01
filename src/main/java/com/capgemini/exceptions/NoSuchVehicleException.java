package com.capgemini.exceptions;

public class NoSuchVehicleException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchVehicleException(String s) {
		super(s);
	}
}
