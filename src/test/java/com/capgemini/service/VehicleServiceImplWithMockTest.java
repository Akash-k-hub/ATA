package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.criteria.internal.expression.SearchedCaseExpression.WhenClause;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import com.capgemini.exceptions.NoSuchVehicleException;
import com.capgemini.model.Address;
import com.capgemini.model.Driver;
import com.capgemini.model.Route;
import com.capgemini.model.Vehicle;
import com.capgemini.repository.VehicleRepository;

@SpringBootTest
class VehicleServiceImplWithMockTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private VehicleService vehicleService;

	@MockBean
	private VehicleRepository vehicleRepository;

	@Test
	void addVehicleTest() {

		Address address = new Address(1, "Dhule", "425401");

		Route route = new Route(1, "Amalner", "Mumbai", 450, 8);

		Driver driver = new Driver();
		driver.setDriverId(1);
		driver.setDriverName("Jake");
		driver.setLicenceNo("UP327869");
		driver.setContactNo("9874563210");
		driver.setDriverAddress(address);

		Vehicle vehicle = new Vehicle();
		vehicle.setDriver(driver);
		vehicle.setFarePerKm(2);
		vehicle.setStatus(true);
		vehicle.setSittingCapacity(30);
		vehicle.setVehicleNo("UP32CD0696");
		vehicle.setVehicleName("Indus Travellers");
		vehicle.setRoute(route);
		vehicle.setVehicleType(vehicle.getVehicleType().AC_SEATER);

		when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
		assertEquals(vehicle, vehicleService.addVehicle(vehicle));

	}

	@Test
	void testGetVehicleById() throws NoSuchVehicleException {
		
		Address address = new Address(2, "Dhule", "436501");

		Route route = new Route(2, "Jaipur", "Mumbai", 300, 8);
		
		Driver driver = new Driver();
		driver.setDriverId(1);
		driver.setDriverName("Jake");
		driver.setLicenceNo("UP327869");
		driver.setContactNo("9874563210");
		driver.setDriverAddress(address);
	
		Vehicle expected = context.getBean(Vehicle.class);
		expected.setVehicleNo("UP54BH0986");
		expected.setVehicleName("Tours and travels");
		expected.setVehicleType(expected.getVehicleType().NON_AC_SEATER);
		expected.setSittingCapacity(20);
		expected.setFarePerKm(3);
		expected.setDriver(driver);
		expected.setRoute(route);
		
		when(vehicleRepository.existsById(expected.getVehicleId())).thenReturn(true);
		Optional<Vehicle> expectation = Optional.of(expected);
		when(vehicleRepository.findById(expected.getVehicleId())).thenReturn(expectation);
		
		}
	
	@Test
	void testFindAllVehicle() {
		
		Address address = new Address(3, "Dummy", "246501");

		Route route = new Route(3, "Jaipur", "Mumbai", 300, 8);
		
		Driver driver = new Driver();
		driver.setDriverId(1);
		driver.setDriverName("Sully");
		driver.setLicenceNo("UP657869");
		driver.setContactNo("9456563210");
		driver.setDriverAddress(address);
		
		Vehicle vehicleTemp = new Vehicle();
		vehicleTemp.setVehicleNo("UP87BH6547");
		vehicleTemp.setVehicleName("Ram Travels");
		vehicleTemp.setVehicleType(vehicleTemp.getVehicleType().AC_SEATER);
		vehicleTemp.setSittingCapacity(20);
		vehicleTemp.setFarePerKm(5);
		vehicleTemp.setDriver(driver);
		vehicleTemp.setRoute(route);
		
		List<Vehicle> list = new ArrayList<Vehicle>();
		list.add(vehicleTemp);
		
		when(vehicleRepository.findAll()).thenReturn(list);
		assertEquals(1, vehicleService.findAllVehicle().size());
	}

}
