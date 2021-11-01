package com.capgemini.service;

import java.util.List;

import com.capgemini.exceptions.NoSuchVehicleException;
import com.capgemini.model.Vehicle;

public interface VehicleService {
	
	public Vehicle addVehicle(Vehicle vehicle);
	public Vehicle findVehicleById(int vehicleId) throws NoSuchVehicleException;
	public List<Vehicle> findAllVehicle();
	public boolean removeVehicleById(int vehicleId) throws NoSuchVehicleException;
	public boolean modifyVehicleById(Vehicle vehicle);

}
