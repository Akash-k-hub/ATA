package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;

import com.capgemini.exceptions.NoCapacityInVehicleException;
import com.capgemini.exceptions.VehicleAlreadyDepartedException;
import com.capgemini.model.Address;
import com.capgemini.model.Booking;
import com.capgemini.model.Customer;
import com.capgemini.model.Driver;
import com.capgemini.model.Route;
import com.capgemini.model.Vehicle;
import com.capgemini.repository.BookingRepository;

@SpringBootTest
class BookingServiceImplMockitoTesting {

	@Autowired
	private BookingService expectedService;
	
	@MockBean
	private BookingRepository expectedRepository;
	
	@Test
	public void saveBookingTest() throws MailException, NoCapacityInVehicleException, MessagingException, VehicleAlreadyDepartedException {
		
	
		Address address = new Address(1, "Dhule", "425401");
		
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setAddress(address);
		customer.setDateOfBirth(LocalDate.of(1996, 04, 18));
		customer.setEmail("lsonawane271@gmail.com");
		customer.setFirstName("Lalit");
		customer.setLastName("Sonawane");
		customer.setGender(customer.getGender());
		customer.setMobileNo(9988776655L);
		customer.setPassword("Green@123");
		
		Route route = new Route(1, "Amalner", "Mumbai", 450, 8);

		Driver driver = new Driver();
		driver.setDriverId(1);
		driver.setContactNo("9876543214");
		driver.setDriverName("Jake");
		driver.setLicenceNo("GH123456");
		driver.setDriverAddress(address);
		
		Vehicle vehicle = new Vehicle();
		vehicle.setFarePerKm(2);
		vehicle.setSittingCapacity(45);
		vehicle.setVehicleId(4);
		vehicle.setVehicleName("Shri Sai");
		vehicle.setVehicleNo("MH 20 CT 9865");
		vehicle.setVehicleType(vehicle.getVehicleType().AC_SEATER);
		vehicle.setStatus(true);
		vehicle.setDriver(driver);
		vehicle.setRoute(route);
		
		Booking expected = new Booking();
		expected.setBookingId(8);
		expected.setBoardingPoint("Hp Gas");
		expected.setDropPoint("Thane");
		expected.setFare(1500);
		//expected.setBookingDateTime(LocalDateTime.of(LocalDate.of(2021, 7, 16),LocalTime.of(12, 25, 00)));
		expected.setNoOfPassengers(3);
		expected.setStatus(true);
		expected.setCustomer(customer);
		expected.setVehicle(vehicle);		
		
		when(expectedRepository.save(expected)).thenReturn(expected);
		assertEquals(expected, expectedService.bookVehicle(expected));
		
	
	}

}
