package com.capgemini.exceptions;

public class NoCapacityInVehicleException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoCapacityInVehicleException() {
		super();
	}

	public NoCapacityInVehicleException(String s) {
		super(s);
	}
}
