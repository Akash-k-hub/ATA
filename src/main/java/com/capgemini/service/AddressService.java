package com.capgemini.service;

import java.util.List;

import com.capgemini.exceptions.NoSuchAddressException;
import com.capgemini.model.Address;

public interface AddressService {
	public Address addAddress(Address address);
	public Address findAddressById(int addressId) throws NoSuchAddressException;
	public List<Address> findAllAddress();
	public int updateCity(int addressId, String city) throws NoSuchAddressException;
	public int updatePincode(int addressId, String pincode) throws NoSuchAddressException;

}
