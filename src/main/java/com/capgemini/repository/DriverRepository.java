package com.capgemini.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
	
//	@Transactional
//	@Modifying
//	@Query(value = "Update Driver d set d.driverName = :driverName where d.driverId = :driverId")
//	int updateDriverName(@Param("driverId")int driverId, @Param("driverName") String name);
//	
//	@Transactional
//	@Modifying
//	@Query(value = "Update Driver d set d.contactNo = :contactNo where d.driverId = :driverId")
//	int updateDriverContactNo(@Param("driverId")int driverId, @Param("contactNo") String contactNo);
//	
//	@Transactional
//	@Modifying
//	@Query(value = "Update Driver d set d.licenceNo = :licenceNo where d.driverId = :driverId")
//	int updateDriverLicenceNo(@Param("driverId")int driverId, @Param("licenceNo") String licenceNo);
	
	@Modifying
	@Transactional
	@Query(value = "Update Driver d Set d.status=false where d.driverId=:driverId")
	int deleteDriverById (@Param("driverId") int driverId );
	
	@Transactional
	@Modifying
	@Query(value="Update Driver d Set d.driverName=:driverName,d.contactNo=:contactNo,d.licenceNo=:licenceNo Where d.driverId=:driverId")
	public int updateDriver(@Param("driverName")String driverName,@Param("contactNo") String contactNo,@Param("licenceNo") String licenceNo,@Param("driverId")int driverId);
}
