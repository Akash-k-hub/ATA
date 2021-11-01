package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import com.capgemini.exceptions.NoSuchCustomerException;
import com.capgemini.model.Customer;
import com.capgemini.repository.CustomerRepository;
@SpringBootTest
class CustomerServiceImplTest {
	@Autowired
	private CustomerService service;
	@Autowired
	private ApplicationContext context;

	@MockBean
	private CustomerRepository repository;
	@Test
	
	public void testFindCustomerByIdShouldReturnCustomer() throws NoSuchCustomerException {
	
	Customer expected = context.getBean(Customer.class);

	
		Customer customer = new Customer();
		expected.setCustomerId(1);
		expected.setFirstName("Alsabah");
		expected.setLastName("Rayeen");
		expected.setDateOfBirth(LocalDate.of(1997,06,04));
		expected.setGender(expected.getGender());
		expected.setMobileNo(8765477783L);
		expected.setEmail("rayeenaab@gmail.com");
		expected.setPassword("56453423");
		expected.getAddress().setAddressId(1);
		expected.getAddress().setCity("Test");
		expected.getAddress().setPincode("000000");
		
		
		when(repository.existsById(expected.getCustomerId())).thenReturn(true);
		Optional<Customer> expectation = Optional.of(expected);
		when(repository.findById(expected.getCustomerId())).thenReturn(expectation);
		
		Customer actual = service.findCustomerById(expected.getCustomerId());
		
	
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getLastName(), actual.getLastName());
		assertEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
		assertEquals(expected.getGender(), actual.getGender());
		assertEquals(expected.getMobileNo(), actual.getMobileNo());
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.getPassword(), actual.getPassword());
	
		assertEquals(expected.getAddress().getAddressId(), actual.getAddress().getAddressId());
		assertEquals(expected.getAddress().getCity(), actual.getAddress().getCity());
		assertEquals(expected.getAddress().getPincode(), actual.getAddress().getPincode());
		
		

	}
}