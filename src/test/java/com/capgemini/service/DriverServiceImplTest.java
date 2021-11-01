package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.capgemini.exceptions.NoSuchDriverException;
import com.capgemini.model.Address;
import com.capgemini.model.Driver;

@SpringBootTest
class DriverServiceImplTest {
	
	@Autowired
	private DriverService service2;
	
	@Autowired
	private ApplicationContext context;


	@Test
	void testUpdateDriverContactNo() throws NoSuchDriverException {
		Driver driver = new Driver();
		Address address = new Address(1,"chennai","602117");
		driver.setDriverAddress(address);
		driver.setDriverId(1);
		driver.setDriverName("Ram");
		driver.setContactNo("8106762539");
		driver.setLicenceNo("AP03202118");
		driver = service2.findDriverById(1);
		//int result = service2.updateDriverContactNo(1,"9876543210");
		assertEquals("9876543210",driver.getContactNo());
	}
	
	@Test
	void testUpdateDriverName() throws NoSuchDriverException {
		Driver driver = new Driver();
		Address address = new Address(1,"chennai","602117");
		driver.setDriverAddress(address);
		driver.setDriverId(1);
		driver.setDriverName("Ram");
		driver.setContactNo("8106762539");
		driver.setLicenceNo("AP03202118");
		driver = service2.findDriverById(1);
		//int result = service2.updateDriverName(1,"Krishna");
		assertEquals("Krishna",driver.getDriverName());
	}
	
	@Test
	void testUpdateDriverLicenceNo() throws NoSuchDriverException {
		Driver driver = new Driver();
		Address address = new Address(1,"chennai","602117");
		driver.setDriverAddress(address);
		driver.setDriverId(1);
		driver.setDriverName("Ram");
		driver.setContactNo("8106762539");
		driver.setLicenceNo("AP03202118");
		driver = service2.findDriverById(1);
		//int result = service2.updateDriverLicenceNo(1,"AP03202120");
		assertEquals("AP03202120",driver.getLicenceNo());
	}
	
	@Test
	public void testShouldThrowNoSuchStudentException() {
		Driver expected = context.getBean(Driver.class);
		NoSuchDriverException exception = Assertions.assertThrows(NoSuchDriverException.class, () -> {
			service2.findDriverById(expected.getDriverId());
		});

	}
	

}
