package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.exceptions.NoSuchAddressException;
import com.capgemini.model.Address;
import com.capgemini.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository repository;
	
	@Override
	public Address addAddress(Address address) {
		return repository.save(address);
	}

	@Override
	public int updateCity(int addressId, String city) throws NoSuchAddressException {
		if(repository.existsById(addressId)) {
			return repository.updateCity(addressId, city);
		}
		throw new NoSuchAddressException("Addressr with id " + addressId + " not found");
	}
	
	@Override
	public int updatePincode(int addressId, String pincode) throws NoSuchAddressException {
		if(repository.existsById(addressId)) {
			return repository.updateDriverPincode(addressId, pincode);
		}
		throw new NoSuchAddressException("Addressr with id " + addressId + " not found");
	}

	@Override
	public List<Address> findAllAddress() {
		return repository.findAll();
	}

	@Override
	public Address findAddressById(int addressId) throws NoSuchAddressException {
		return repository.findById(addressId).get();
	}


}
