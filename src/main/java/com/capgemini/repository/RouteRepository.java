package com.capgemini.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.Route;

public interface RouteRepository extends JpaRepository<Route, Integer> {

//	@Modifying
//	@Transactional
//	@Query(value = "Update Route r Set r.source= :source where r.routeId= :routeId")
//	int updateRouteSource(@Param("source") String source, @Param("routeId") int routeId);
//
//	@Modifying
//	@Transactional
//	@Query(value = "Update Route r Set r.destination= :destination where r.routeId= :routeId")
//	int updateRouteDestination(@Param("destination") String destination, @Param("routeId") int routeId);
//
//	@Modifying
//	@Transactional
//	@Query(value = "Update Route r Set r.distance= :distance where r.routeId= :routeId")
//	int updateRouteDistance(@Param("distance") double distance, @Param("routeId") int routeId);
//
//	@Modifying
//	@Transactional
//	@Query(value = "Update Route r Set r.duration= :duration where r.routeId= :routeId")
//	int updateRouteDuration(@Param("duration") double duration, @Param("routeId") int routeId);
//	
	@Modifying
	@Transactional
	@Query(value = "Update Route r Set r.status=false where r.routeId=:routeId")
	int deleteRouteById (@Param("routeId") int routeId );
	
	@Modifying
	@Transactional
	@Query(value = "Update Route r Set r.source= :source, r.destination= :destination, r.distance= :distance,"
			+ " r.duration= :duration Where r.routeId= :routeId")
	public int modifyVehicleRoute1(@Param("source") String source, @Param("destination") String destination,
			@Param("distance") double distance,
			@Param("duration") double duration,@Param("routeId") int routeId);
}
