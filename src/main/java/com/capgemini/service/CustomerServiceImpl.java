package com.capgemini.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.capgemini.exceptions.NoSuchCustomerException;
import com.capgemini.model.Customer;
import com.capgemini.repository.AddressRepository;
import com.capgemini.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	private AddressRepository addressRepository; 

	
	@Override
	@Transactional
	public  Customer registration(Customer customer)  {
		Customer result = null;
			customer.setAddress(addressRepository.findById(customer.getAddress().getAddressId()).get());
			result =  repository.save(customer);
		
		return result;
	}

	@Override
	public Customer findCustomerById(int customerId) throws NoSuchCustomerException {

		return repository.findById(customerId).get(); 

	}

	@Override
	public List<Customer> findAllCustomers() {

		return repository.findAll();
	}

	@Override
	public List<Customer> findAllCustomerByName(String name) {
		return repository.realAllName(name);
	}

	@Override
	public boolean deleteCustomerById(int customerId) throws NoSuchCustomerException {
		if (repository.existsById(customerId)) {
			repository.deleteById(customerId);
			return true;
		}
		return false;

	}

	
	@Override
	public Customer loginCustomer(Customer customer) {
		
		Customer customer2 = repository.readByEmailAndpassword(customer.getEmail(), customer.getPassword());
		
		return customer2;
	}

	@Autowired
	@Override
	public void MailService(JavaMailSender javaMailSender) {
		// TODO Auto-generated method stub
		this.javaMailSender = javaMailSender;
	}

	@Override
	public void sendEmail(Customer customer) throws MailException, MessagingException {

		// TODO Auto-generated method stub

		String result1 = customer.getPassword();
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(customer.getEmail());
		helper.setSubject(" Automation Travel Agency");
	//	helper.setText("Welcome To Automation Travel Agency");
	//	helper.setText("hello");
	//	helper.setText(result1);

		helper.setText("Welcome To Automation Travel Agency"  
		+"\nYour Password is :    " + result1);
		
		javaMailSender.send(mimeMessage);
	}

	@Override
	public Customer findCustomerByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.readByEmail(email);
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		repository.updateCustomer(customer.getFirstName(), customer.getLastName(), customer.getDateOfBirth(), customer.getMobileNo(),customer.getEmail(), customer.getPassword(),  customer.getCustomerId());
		return true;
	}

//	@Override
//	public int updateCustomerFirstName(int customerId, String firstName) throws NoSuchCustomerException {
//		// TODO Auto-generated method stub
//		if (repository.existsById(customerId)) {
//			return repository.updateCustomerFirstName(customerId, firstName);
//		}
//		throw new NoSuchCustomerException("Customer with id " + customerId + " not found");
//	}
//
//
//	@Override
//	public int updateCustomerLastName(int customerId, String lastName) throws NoSuchCustomerException {
//		// TODO Auto-generated method stub
//		if (repository.existsById(customerId)) {
//			return repository.updateCustomerlastName(customerId, lastName);
//		}
//		throw new NoSuchCustomerException("Customer with id " + customerId + " not found");
//	}
//
//	@Override
//	public int updateCustomerMobileNo(int customerId, long mobileNo) throws NoSuchCustomerException {
//		// TODO Auto-generated method stub
//		if (repository.existsById(customerId)) {
//			return repository.updateCustomerMobileNo(customerId, mobileNo);
//		}
//		throw new NoSuchCustomerException("Customer with id " + customerId + " not found");
//	}

}
