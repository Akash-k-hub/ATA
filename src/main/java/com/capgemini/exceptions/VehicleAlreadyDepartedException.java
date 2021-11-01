package com.capgemini.exceptions;

public class VehicleAlreadyDepartedException extends Exception {

	private static final long serialVersionUID = 1L;

	public VehicleAlreadyDepartedException(String s) {
		super(s);
	}
}
