package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import com.capgemini.exceptions.NoSuchVehicleException;
import com.capgemini.model.Vehicle;

@SpringBootTest
class VehicleServiceImplTest {

	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ApplicationContext context;
	
	@Test
	void testFindVehicleByIdShouldReturnVehicle() throws NoSuchVehicleException {
		
		Vehicle expected = context.getBean(Vehicle.class);
		expected.setVehicleName("testVehicl");
		expected.setVehicleNo("test3");
		expected.setVehicleType(expected.getVehicleType().AC_SEATER);
		expected.setFarePerKm(3);
		expected.setSittingCapacity(30);
		
		expected.getDriver().setDriverId(1);
		expected.getDriver().setContactNo("9874563210");
		expected.getDriver().setDriverName("Test");
		expected.getDriver().setLicenceNo("test0000");
		
		expected.getDriver().getDriverAddress().setAddressId(1);
		expected.getDriver().getDriverAddress().setCity("test");
		expected.getDriver().getDriverAddress().setPincode("000000");
		
		expected.getRoute().setRouteId(1);
		expected.getRoute().setSource("test");
		expected.getRoute().setDestination("test");
		expected.getRoute().setDistance(345);
		expected.getRoute().setDuration(2);
		
		vehicleService.addVehicle(expected);
		
		Vehicle actual = vehicleService.findVehicleById(expected.getVehicleId());
		
		assertEquals(expected.getVehicleId(), actual.getVehicleId());
		assertEquals(expected.getVehicleName(), actual.getVehicleName());
		assertEquals(expected.getVehicleNo(), actual.getVehicleNo());
		assertEquals(expected.getFarePerKm(), actual.getFarePerKm());
		assertEquals(expected.getSittingCapacity(), actual.getSittingCapacity());
		
		assertEquals(expected.getDriver().getDriverId(), actual.getDriver().getDriverId());
		assertEquals(expected.getDriver().getContactNo(), actual.getDriver().getContactNo());
		assertEquals(expected.getDriver().getDriverName(), actual.getDriver().getDriverName());
		assertEquals(expected.getDriver().getLicenceNo(), actual.getDriver().getLicenceNo());
		
		assertEquals(expected.getDriver().getDriverAddress().getAddressId(), actual.getDriver().getDriverAddress().getAddressId());
		assertEquals(expected.getDriver().getDriverAddress().getCity(), actual.getDriver().getDriverAddress().getCity());
		assertEquals(expected.getDriver().getDriverAddress().getPincode(), actual.getDriver().getDriverAddress().getPincode());
		
		assertEquals(expected.getRoute().getRouteId(), actual.getRoute().getRouteId());
		assertEquals(expected.getRoute().getSource(), actual.getRoute().getSource());
		assertEquals(expected.getRoute().getDestination(), actual.getRoute().getDestination());
		assertEquals(expected.getRoute().getDistance(), actual.getRoute().getDistance());
		assertEquals(expected.getRoute().getDuration(), actual.getRoute().getDuration());
		
//		vehicleService.removeVehicleById(expected.getVehicleId());
	}

}
