package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.exceptions.NoSuchAdminException;
import com.capgemini.model.Address;
import com.capgemini.model.Admin;
import com.capgemini.repository.AdminRepository;
@SpringBootTest
class AdminServiceImplWithMockTest {

	@Autowired
	private AdminService service;
	
	@MockBean
	private AdminRepository repository;
	
	
	@Test
	 void saveAdminTest() throws NoSuchAdminException
	{
		
		
		Address address = new Address(1, "Dhule", "425401");
		
		Admin admin=new Admin();
		
	//	admin.setAdminId(1);
		admin.setFirstName("eest");
		admin.setLastName("Test");
		admin.setDateOfBirth(LocalDate.of(1998,02,03));
		admin.setMobileNo(987687);
		admin.setEmail("yogesh10@gmail.com");
		admin.setPassword("345324234");
		admin.setAddress(address);
		when(repository.save(admin)).thenReturn(admin);
		assertEquals(admin, service.registration(admin));
		
	}
	
	}