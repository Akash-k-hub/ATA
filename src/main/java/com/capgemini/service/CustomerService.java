package com.capgemini.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import com.capgemini.exceptions.NoSuchCustomerException;
import com.capgemini.model.Customer;

public interface CustomerService {
	public Customer registration(Customer customer);

	public Customer findCustomerById(int customerId) throws NoSuchCustomerException;

	public List<Customer> findAllCustomers();

	public List<Customer> findAllCustomerByName(String name);

	public boolean deleteCustomerById(int customerId) throws NoSuchCustomerException;

	public Customer loginCustomer(Customer customer);

	public void MailService(JavaMailSender javaMailSender);

	public void sendEmail(Customer customer) throws MailException, MessagingException;

	public Customer findCustomerByEmail(String email);
	
	public boolean updateCustomer(Customer customer);

//	public int updateCustomerFirstName(int customerId, String firstName) throws NoSuchCustomerException; 
//
//	public int updateCustomerLastName(int customerId, String lastName) throws NoSuchCustomerException; 
//	
//	public int updateCustomerMobileNo(int customerId, long mobileNo) throws NoSuchCustomerException; 

}