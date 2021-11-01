package com.capgemini.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.capgemini.exceptions.NoSuchVehicleException;
import com.capgemini.model.Vehicle;
import com.capgemini.repository.DriverRepository;
import com.capgemini.repository.RouteRepository;
import com.capgemini.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleRepository repository;

	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Transactional
	@Override
	public Vehicle addVehicle(Vehicle vehicle) {
		vehicle.setStatus(true);
		vehicle.setDriver(driverRepository.findById(vehicle.getDriver().getDriverId()).get());
		vehicle.setRoute(routeRepository.findById(vehicle.getRoute().getRouteId()).get());
		vehicle = repository.save(vehicle);
		return vehicle;
	}

	@Override
	public Vehicle findVehicleById(int vehicleId) throws NoSuchVehicleException {
		if(repository.existsById(vehicleId)) {
			return repository.findById(vehicleId).get();
		}
		throw new NoSuchVehicleException("Vehicle with id " + vehicleId + " not found.");
	}

	@Override
	public List<Vehicle> findAllVehicle() {
			return repository.findAll();
	}

	@Override
	public boolean removeVehicleById(int vehicleId) throws NoSuchVehicleException {
		if(repository.existsById(vehicleId)) {
			repository.deleteVehicleById(vehicleId);
		return true;
		}
		throw new NoSuchVehicleException("Vehicle with id " + vehicleId + " not found.");
	}
    
//	@Override
//	public Vehicle modifyVehicleById(Vehicle vehicle) {
//		vehicle.setDriver(driverRepository.findById(vehicle.getDriver().getDriverId()).get());
//		vehicle.setRoute(routeRepository.findById(vehicle.getRoute().getRouteId()).get());
//		vehicle.setVehicleType();
//		return repository.save(vehicle);
//	}
	
	@Override
	public boolean modifyVehicleById(Vehicle vehicle) {
		repository.updateVehicle(vehicle.getVehicleNo(), vehicle.getVehicleName(),vehicle.getSittingCapacity(), vehicle.getVehicleType(),
				vehicle.getFarePerKm(), vehicle.getJourneyDate(),vehicle.getDepartTime(), vehicle.getVehicleId());
		return true;
	}

}