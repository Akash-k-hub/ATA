package com.capgemini.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exceptions.NoSuchAddressException;
import com.capgemini.exceptions.NoSuchAdminException;
import com.capgemini.exceptions.NoSuchCustomerException;
import com.capgemini.model.Address;
import com.capgemini.model.Admin;
import com.capgemini.model.Customer;
import com.capgemini.service.AddressService;
import com.capgemini.service.AdminService;
import com.capgemini.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping(path = "loginreg")
public class LoginRegistrationController {
	Logger logger = LoggerFactory.getLogger(LoginRegistrationController.class);

	@Autowired
	private AdminService service;

	@Autowired
	private Admin admin;

	@Autowired
	private AddressService addressService;

	// http://localhost:9090/user-api/loginreg/admin/ - Post
	// All the admin data will be saved in the database
	@PostMapping(path = "/admin/")
	public ResponseEntity<String> saveAdmin(@Valid @RequestBody Admin admin) throws NoSuchAdminException {
		ResponseEntity<String> response = null;
		logger.info("Save admin method is called");
		Admin result = service.registration(admin);
		if (result != null) {
			response = new ResponseEntity<String>("Admin with id" + admin.getAdminId() + "is added",
					HttpStatus.CREATED);
			logger.info("Admin is Created");
		} else {

			response = new ResponseEntity<String>("Admin with id" + admin.getAdminId() + "is not added",
					HttpStatus.UNPROCESSABLE_ENTITY);
			logger.warn("Creation of admin is failed");
			throw new NoSuchAdminException("Admin is not saved");
		}
		return response;
	}

	// http://localhost:9090/user-api/loginreg/updateAdmin/ - Put
	@PutMapping(path = "/updateAdmin/")
				public ResponseEntity<String> udpateAdmin(@RequestBody Admin admin) {
					ResponseEntity<String> response = null;
					boolean result = service.updateAdmin(admin);
					if (result)
						response = new ResponseEntity<String>("admin with id " + admin.getAdminId() + " is updated.",
								HttpStatus.CREATED);
					return response;
	}
					
	// http://localhost:9090/user-api/loginreg/admin/1 - Get
	// we can get the data of admin by providing id and all the data will be
	// displayed
	@GetMapping(path = "/admin/{adminId}")
	public ResponseEntity<Admin> getAdminById(@PathVariable("adminId") int adminId) throws NoSuchAdminException {
		ResponseEntity<Admin> response = null;
		Admin admin = service.findAdminById(adminId);
		response = new ResponseEntity<Admin>(admin, HttpStatus.OK);
		logger.info("Searching for admin using Id");
		return response;
	}

	// http://localhost:9090/user-api/loginreg/admin/ - Get
	// We can get the data of all the admins present in the database
	@GetMapping(path = "/admin/")
	public ResponseEntity<List<Admin>> getAllAdmins() {
		ResponseEntity<List<Admin>> response = null;
		List<Admin> list = service.findAllAdmins();
		response = new ResponseEntity<List<Admin>>(list, HttpStatus.OK);
		logger.info("Geting all Admins");
		return response;
	}

	// http://localhost:9090/user-api/loginreg/admin/byName/xyz
	// we can get the data from the database by name
	@GetMapping(path = "/admin/byName/{firstName}")
	public ResponseEntity<List<Admin>> getAllAdminByName(@PathVariable("firstName") String firstName)
			throws NoSuchAdminException {
		ResponseEntity<List<Admin>> response = null;
		List<Admin> list = service.findAllAdminByName(firstName);
		if (list.isEmpty()) {
			throw new NoSuchAdminException("admin does not exist");
		}
		response = new ResponseEntity<List<Admin>>(list, HttpStatus.OK);
		logger.info("Searching for admin using first name");
		return response;

	}

	// http://localhost:9090/user-api/loginreg/delete/2
	// we can delete the data from database by providing id and all the data will be
	// deleted
	@DeleteMapping("/delete/{adminId}")
	public String deleteAdmin(@PathVariable(value = "adminId") int adminId) throws NoSuchAdminException {

		service.deleteAdmin(adminId);
		logger.warn("Admin is deleted");
		return "Deleted Successfully id= " + adminId;
	}

	// http://localhost:9090/user-api/loginreg/login/abcds@gmail.com/5678@etrtte
	// we can login by providing email id and password
	// http://localhost:9090/user-api/loginreg/loginAdmin/
			@PostMapping(path = "/loginAdmin/")
			public ResponseEntity<String> getAdminByEmailAndPassword(@RequestBody Admin admin) {
				ResponseEntity<String> response = null;
				boolean result = service.loginAdmin(admin);
				if (result) {
					response = new ResponseEntity<String>("Login successfully", HttpStatus.OK);
				} else {
					response = new ResponseEntity<String>("Login Failed , Please Enter Right Username and Passsword",
							HttpStatus.INTERNAL_SERVER_ERROR);
					logger.trace("Login Successfull");
				}
				return response;
			}

	// http://localhost:9090/user-api/loginreg/send-mail/ybiradar10@gmail.com
	/*
	 * @RequestMapping(path="/send-mail/") public String send() {
	 * 
	 * 
	 * //String email=email1; admin.setEmail("ybiradar10@gmail.com"); //Receiver's
	 * email address //admin.setEmail(email); try { service.sendEmail(admin); }
	 * catch (MailException mailException) { System.out.println(mailException); }
	 * return "Congratulations! Your mail has been send to the user."; }
	 */
//	http://localhost:9090/user-api/loginreg/admin/byemail/ybiradar10@gmail.com
	// By giving the email id
	@GetMapping(path = "/admin/byemail/{email}")
	public String forgotAdminPassword(@PathVariable("email") String email)
			throws MailException, MessagingException, NoSuchAdminException {
		String message = "";

		Admin result = service.findAdminByEmail(email);
		if (result != null) {
			String email1 = result.getEmail();

			String password1 = result.getPassword();
			admin.setEmail(email1);
			admin.setPassword(password1);

			service.sendEmail(admin);
			logger.warn("Password is sent to Email Id");
			message = "Congratulations! Your mail has been send to the user.";
		}

		else {
			throw new NoSuchAdminException("No email id found");

		}
		return message;
	}

	// -------------------------------------------------------------------------------------------------------------

	@Autowired
	private CustomerService customerService;

	@Autowired
	private Customer customer;

	// http://localhost:9090/user-api/loginreg/customer/byemail/abc@gmail.com - Get
	@GetMapping(path = "/customer/byemail/{email}")
	public String forgotCustomerPassword(@PathVariable("email") String email) throws MailException, MessagingException {

		Customer result = customerService.findCustomerByEmail(email);
		String email1 = result.getEmail();
		String password1 = result.getPassword();
		customer.setEmail(email1);
		customer.setPassword(password1);

		customerService.sendEmail(customer);
		logger.info("Customer is Created");
		return "Congratulations! Your mail has been send to the user.";
	}

//	http://localhost:9090/user-api/loginreg/customer/    - post
	@PostMapping(path = "/customer/")
	public ResponseEntity<String> saveCustomer(@Valid @RequestBody Customer customer) {
		ResponseEntity<String> response = null;
		Customer result = customerService.registration(customer);
		if (result != null)
			response = new ResponseEntity<String>("Customer with id " + customer.getCustomerId() + " is added.",
					HttpStatus.CREATED);
		return response;
	}

	// http://localhost:9090/user-api/loginreg/searchById/1 -Get
	@GetMapping(path = "/searchById/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") int customerId)
			throws NoSuchCustomerException {
		ResponseEntity<Customer> response = null;
		Customer customer = customerService.findCustomerById(customerId);
		response = new ResponseEntity<Customer>(customer, HttpStatus.OK);
		logger.info("Searching for Customer using Id");
		return response;
	}

	// http://localhost:9090/user-api/loginreg/getAllcustomer/ -Get
	@GetMapping(path = "/getAllcustomer/")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		ResponseEntity<List<Customer>> response = null;
		List<Customer> list = customerService.findAllCustomers();
		response = new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
		logger.info("Geting all Customer details");
		return response;
	}

	// http://localhost:9090/user-api/loginreg/searchByName/Shagufta
	@GetMapping(path = "/searchByName/{firstName}")
	public ResponseEntity<List<Customer>> getAllCustomerByName(@PathVariable("firstName") String firstName) {
		ResponseEntity<List<Customer>> response = null;
		List<Customer> list = customerService.findAllCustomerByName(firstName);
		response = new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
		logger.info("Searching for Customers using first name");
		return response;
	}

	// http://localhost:9090/user-api/loginreg/deleteCustomerById/2
	@DeleteMapping("/deleteCustomerById/{customerId}")
	public ResponseEntity<String> removeCustomer(@PathVariable("customerId") int customerId)
			throws NoSuchCustomerException {
		ResponseEntity<String> response = null;
		boolean result = customerService.deleteCustomerById(customerId);
		if (result)
			response = new ResponseEntity<String>("Customer with id = " + customerId + " is deleted.", HttpStatus.OK);
		logger.warn("Customer is Deleted");
		return response;
	}

	// http://localhost:9090/user-api/loginreg/loginCustomer/
	@PostMapping(path = "/loginCustomer/")
	public ResponseEntity<Customer> getCustomerByEmailAndPassword(@RequestBody Customer customer) {
		ResponseEntity<Customer> response = null;
		Customer result = customerService.loginCustomer(customer);
		if (result!=null) {
			response = new ResponseEntity<Customer>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<Customer>(result,
					HttpStatus.INTERNAL_SERVER_ERROR);
			logger.trace("Login Successfull");
		}
		return response;
	}
	// http://localhost:9090/user-api/loginreg/loginCustomer/
//		@GetMapping(path = "/searchByEmail/")
//		public ResponseEntity<String> getCustomerByEmail(@RequestBody Customer customer) {
//			ResponseEntity<String> response = null;
//			Customer result = customerService.findCustomerByEmail(customer);
//				response = new ResponseEntity<String>("Login successfully", HttpStatus.OK);
//			} else {
//				response = new ResponseEntity<String>("Login Failed , Please Enter Right Username and Passsword",
//						HttpStatus.INTERNAL_SERVER_ERROR);
//				logger.trace("Login Successfull");
//			}
//			return response;
//		}

	// http://localhost:9090/user-api/loginreg/updateCustomer/ - Put
	@PutMapping(path = "/updateCustomer/")
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
		ResponseEntity<String> response = null;
		boolean result = customerService.updateCustomer(customer);
		if (result)
			response = new ResponseEntity<String>("customer with id " + customer.getCustomerId() + " is updated.",
					HttpStatus.CREATED);
		return response;
	}

//// 	http://localhost:9090/user-api/loginreg/updateCustomerFirstName/1/Alsabah
//	@PutMapping(path = "/updateCustomerFirstName/{customerId}/{customerFirstName}")
//	public ResponseEntity<String> updateCustomerFirstName(@PathVariable("customerFirstName") String customerFirstName,
//			@PathVariable("customerId") int customerId) throws NoSuchCustomerException {
//		ResponseEntity<String> response = null;
//		// Driver driver = null;
//		int result = customerService.updateCustomerFirstName(customerId, customerFirstName);
//		if (result > 0)
//			response = new ResponseEntity<String>("Customer Name with id " + customerId + " is updated", HttpStatus.OK);
//		logger.info("Customer first name is updated");
//		return response;
//	}
//
//// 	http://localhost:9090/user-api/loginreg/updateCustomerLastName/1/Rayeen
//	@PutMapping(path = "/updateCustomerLastName/{customerId}/{customerLastName}")
//	public ResponseEntity<String> updateCustomerLastName(@PathVariable("customerLastName") String customerLastName,
//			@PathVariable("customerId") int customerId) throws NoSuchCustomerException {
//		ResponseEntity<String> response = null;
//		// Driver driver = null;
//		int result = customerService.updateCustomerLastName(customerId, customerLastName);
//		if (result > 0)
//			response = new ResponseEntity<String>("Customer Name with id " + customerId + " is updated", HttpStatus.OK);
//		logger.info("Customer last name is updated");
//		return response;
//	}
//
//// 	http://localhost:9090/user-api/loginreg/updateCustomerMobileNo/1/8976542311
//	@PutMapping(path = "/updateCustomerMobileNo/{customerId}/{customerMobileNo}")
//	public ResponseEntity<String> updateCustomerMobileNo(@PathVariable("customerMobileNo") long customerMobileNo,
//			@PathVariable("customerId") int customerId) throws NoSuchCustomerException {
//		ResponseEntity<String> response = null;
//		// Driver driver = null;
//		int result = customerService.updateCustomerMobileNo(customerId, customerMobileNo);
//		if (result > 0)
//			response = new ResponseEntity<String>("Customer Name with id " + customerId + " is updated", HttpStatus.OK);
//		logger.info("Customer mobile number is updated");
//		return response;
//	}

	// http://localhost:9090/user-api/loginreg/saveAddress - POST
	@PostMapping(path = "/saveAddress")
	public ResponseEntity<String> saveAddress(@Valid @RequestBody Address address) {
		ResponseEntity<String> response = null;
		Address result = addressService.addAddress(address);
		if (result != null)
			response = new ResponseEntity<String>("Address with id " + address.getAddressId() + " is added.",
					HttpStatus.CREATED);
		logger.info("Address is saved");
		return response;
	}

	// http://localhost:9090/user-api/loginreg/getAllAddress -GET
	@GetMapping(path = "/getAllAddress")
	public ResponseEntity<List<Address>> getAllAddress() {
		ResponseEntity<List<Address>> response = null;
		List<Address> list = addressService.findAllAddress();
		response = new ResponseEntity<List<Address>>(list, HttpStatus.OK);
		logger.info("All address are displayed");
		return response;
	}

	// http://localhost:9090/user-api/loginreg/updateCity/1/Hyderabad
	@PutMapping(path = "/updateCity/{addressId}/{city}")
	public ResponseEntity<String> updateCity(@PathVariable("city") String city,
			@PathVariable("addressId") int addressId) throws NoSuchAddressException {
		ResponseEntity<String> response = null;
		int result = addressService.updateCity(addressId, city);
		if (result > 0)
			response = new ResponseEntity<String>("Driver Name with id " + addressId + " is updated", HttpStatus.OK);
		logger.info("City is updated");
		return response;
	}

	// http://localhost:9090/user-api/admin/updatePincode/1/600001 - PUT
	@PutMapping(path = "updatePincode/{addressId}/{pincode}")
	public ResponseEntity<String> updatePincode(@PathVariable("pincode") String pincode,
			@PathVariable("addressId") int addressId) throws NoSuchAddressException {
		ResponseEntity<String> response = null;
		int result = addressService.updatePincode(addressId, pincode);
		if (result > 0)
			response = new ResponseEntity<String>("Address with id " + addressId + " is updated", HttpStatus.OK);
		logger.info("Pincode is Updated");
		return response;
	}

}