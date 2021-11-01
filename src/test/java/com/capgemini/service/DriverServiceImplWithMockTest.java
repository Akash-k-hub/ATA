package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import com.capgemini.exceptions.NoSuchDriverException;
import com.capgemini.model.Address;
import com.capgemini.model.Driver;
import com.capgemini.repository.DriverRepository;

@SpringBootTest
class DriverServiceImplWithMockTest {

	@Autowired
	private DriverService service2;
	
	@Autowired
	private ApplicationContext context;
	
	@MockBean
	private DriverRepository repository;
	
	@Test
	void testGetAllDriversShouldReturnDriversList() {
		Address address = new Address(1,"chennai","602117");
		Driver driver = new Driver();
		driver.setDriverAddress(address);
		driver.setDriverId(1);
		driver.setDriverName("Ram");
		driver.setContactNo("8106762539");
		driver.setLicenceNo("AP03202118");
		List<Driver> list = new ArrayList<Driver>();
		list.add(driver);
		when(repository.findAll()).thenReturn(list);
		assertEquals(1, service2.findAllDriver().size());
	}
	
	@Test
	void testFindDriverByIdShouldReturnDriver() throws NoSuchDriverException{
		Driver expected = context.getBean(Driver.class);
		expected.setDriverId(1);
		expected.setDriverName("Test");
		expected.setContactNo("0000000000");
		expected.setLicenceNo("AP0000000000");
		expected.getDriverAddress().setAddressId(1);
		expected.getDriverAddress().setCity("Test");
		expected.getDriverAddress().setPincode("000000");
		
		when(repository.existsById(expected.getDriverId())).thenReturn(true);
		Optional<Driver> expectation = Optional.of(expected);
		when(repository.findById(expected.getDriverId())).thenReturn(expectation);
		
		Driver actual = service2.findDriverById(expected.getDriverId());
		//assertEquals(expected, actual);
		assertEquals(expected.getDriverId(), actual.getDriverId());
		assertEquals(expected.getDriverName(), actual.getDriverName());
		assertEquals(expected.getContactNo(), actual.getContactNo());
		assertEquals(expected.getLicenceNo(), actual.getLicenceNo());
		assertEquals(expected.getDriverAddress().getAddressId(), actual.getDriverAddress().getAddressId());
		assertEquals(expected.getDriverAddress().getCity(), actual.getDriverAddress().getCity());
		assertEquals(expected.getDriverAddress().getPincode(), actual.getDriverAddress().getPincode());
	}
	
	@Test
	void testSaveDriverShouldReturnDriver() {
		Address address = new Address(1,"chennai","602117");
		Driver driver = new Driver();
		driver.setDriverAddress(address);
		driver.setDriverId(1);
		driver.setDriverName("Ram");
		driver.setContactNo("8106762539");
		driver.setLicenceNo("AP03202118");
		when(repository.save(driver)).thenReturn(driver);
		assertEquals(driver, service2.addDriver(driver));
	}
	
	
	@Test
	void testFindDriverByIdShouldThrownNoSuchDriverexception() throws NoSuchDriverException{
		int driverId = -1;
		when(repository.existsById(driverId)).thenReturn(false);
		assertThrows(NoSuchDriverException.class, () -> service2.findDriverById(driverId));
	}
	
	
	
	
	

}
