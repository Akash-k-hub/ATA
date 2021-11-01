package com.capgemini.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "Update Address a set a.city = :city where a.addressId = :addressId")
	int updateCity(@Param("addressId")int addressId, @Param("city") String city);
	
	@Transactional
	@Modifying
	@Query(value = "Update Address a set a.pincode = :pincode where a.addressId = :addressId")
	int updateDriverPincode(@Param("addressId")int addressId, @Param("pincode") String pincode);

}
