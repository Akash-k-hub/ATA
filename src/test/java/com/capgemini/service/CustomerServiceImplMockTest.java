package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import com.capgemini.exceptions.NoSuchCustomerException;
import com.capgemini.model.Address;
import com.capgemini.model.Customer;
import com.capgemini.repository.CustomerRepository;

@SpringBootTest
class CustomerServiceImplMockTest {

	@Autowired
	private CustomerService service;
	@Autowired
	private ApplicationContext context;

	
	@MockBean
	private CustomerRepository repository;
	@Test
	public void saveCustomerTest() throws NoSuchCustomerException {
		

		Address address = new Address(1, "Khandwa", "450001");
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("Alsabah");
		customer.setLastName("Rayeen");
		customer.setDateOfBirth(LocalDate.of(1997,06,04));
		customer.setAddress(address);
		customer.setGender(customer.getGender());
		customer.setMobileNo(8756688223L);
		customer.setEmail("rayeenea@gmail.com");
		customer.setPassword("56453423");
		
		when(repository.save(customer)).thenReturn(customer);
		assertEquals(customer, service.registration(customer));
		
		

	}
	@Test
	public void getAllCustomersTest() {
		

		Address address = new Address(1, "Khandwa", "450001");
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("Alsabah");
		customer.setLastName("Rayeen");
		customer.setDateOfBirth(LocalDate.of(1997,06,04));
		customer.setAddress(address);
		customer.setGender(customer.getGender());
		customer.setMobileNo(8756688223L);
		customer.setEmail("rayeenea@gmail.com");
		customer.setPassword("56453423");
	
		List<Customer> list = new ArrayList<Customer>();
		list.add(customer);
		when(repository.findAll()).thenReturn(list);
		assertEquals(1, service.findAllCustomers().size());
}
	
	
}