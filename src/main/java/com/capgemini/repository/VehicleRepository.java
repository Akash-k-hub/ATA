package com.capgemini.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Vehicle;
import com.capgemini.model.Vehicle.VehicleType;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	@Transactional
	@Modifying
	@Query(value = "Update Vehicle v set v.vehicleNo =:vehicleNo, v.vehicleName =:vehicleName, v.sittingCapacity=:sittingCapacity, "
			+ "v.vehicleType=:vehicleType, v.farePerKm=:farePerKm, v.journeyDate=:journeyDate, v.departTime=:departTime where v.vehicleId=:vehicleId ")
	public int updateVehicle(@Param("vehicleNo") String vehicleNo, @Param("vehicleName") String vehicleName,
			@Param("sittingCapacity") int sittingCapacity, @Param("vehicleType") VehicleType vehicleType,
			@Param("farePerKm") int farePerKm, @Param("journeyDate") LocalDate localDate, @Param("departTime") LocalTime localTime,
			@Param("vehicleId") int VehicleId);

	@Modifying
	@Transactional
	@Query(value = "Update Vehicle v Set v.status = false where v.vehicleId= :vehicleId")
	int deleteVehicleById(@Param("vehicleId") int vehicleId);

	@Query(value = "SELECT v, r FROM Vehicle v JOIN v.route r where r.source = :source and r.destination = :destination and v.journeyDate = :journeyDate")
	public List<Vehicle> searchByCustomerRequest(@Param("source") String source, @Param("destination") String destination, @Param("journeyDate") LocalDate journeyDate);
}
