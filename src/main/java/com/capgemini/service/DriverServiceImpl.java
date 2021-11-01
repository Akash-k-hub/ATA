package com.capgemini.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.exceptions.NoSuchDriverException;
import com.capgemini.model.Driver;
import com.capgemini.repository.AddressRepository;
import com.capgemini.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {
	@Autowired
	private DriverRepository repository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	@Transactional
	public Driver addDriver(Driver driver) {
		driver.setStatus(true);
		driver.setDriverAddress(addressRepository.findById(driver.getDriverAddress().getAddressId()).get());
		driver = repository.save(driver);
		return driver;
	}

	@Override
	public Driver findDriverById(int driverId) throws NoSuchDriverException {
		if (repository.existsById(driverId)) {
			return repository.findById(driverId).get();
		}
		throw new NoSuchDriverException("Driver with id " + driverId + " not found");
	}

	@Override
	public List<Driver> findAllDriver() {
		return repository.findAll();
	}

//	@Override
//	public int updateDriverName(int driverId, String driverName) throws NoSuchDriverException {
//		if (repository.existsById(driverId)) {
//			return repository.updateDriverName(driverId, driverName);
//		}
//		throw new NoSuchDriverException("Driver with id " + driverId + " not found");
//	}
//
//	@Override
//	public int updateDriverContactNo(int driverId, String contactNo) throws NoSuchDriverException {
//		if (repository.existsById(driverId)) {
//			return repository.updateDriverContactNo(driverId, contactNo);
//		}
//		throw new NoSuchDriverException("Driver with id " + driverId + " not found");
//	}
//
//	@Override
//	public int updateDriverLicenceNo(int driverId, String licenceNo) throws NoSuchDriverException {
//		if (repository.existsById(driverId)) {
//			return repository.updateDriverLicenceNo(driverId, licenceNo);
//		}
//		throw new NoSuchDriverException("Driver with id " + driverId + " not found");
//	}

	@Override
	public boolean removeDriverById(int driverId) throws NoSuchDriverException {
		if (repository.existsById(driverId)) {
			repository.deleteDriverById(driverId);
			return true;
		}
		throw new NoSuchDriverException("Driver with id " + driverId + " not found");
	}
	
	@Override
	public boolean updateDriver1(Driver driver) {
		repository.updateDriver(driver.getDriverName(),driver.getContactNo(),driver.getLicenceNo(),driver.getDriverId());
		return true;
	}

	

}
