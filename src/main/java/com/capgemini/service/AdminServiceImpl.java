package com.capgemini.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.capgemini.exceptions.NoSuchAdminException;
import com.capgemini.model.Admin;
import com.capgemini.repository.AddressRepository;
import com.capgemini.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repository;
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Admin registration(Admin admin)throws NoSuchAdminException
	{
		Admin result = null;
		
		admin.setAddress(addressRepository.findById(admin.getAddress().getAddressId()).get());
		
			result = repository.save(admin);
			//result=true;
		
		return result;

	}
	
	
	/*@Override
	public boolean registration(Admin admin) {
		boolean result = false;
		if(AdminValidator.isAdminFirstNameValid(admin.getFirstName()) && AdminValidator.isAdminLastNameValid(admin.getLastName()) )
		{
			admin = repository.save(admin);
			result=true;
		}
		
		return result;

	}*/
	
	
	
	/*@Override
	public boolean registration(Admin admin) {
		boolean result = false;
		admin = repository.save(admin);
		if (admin.getAdminId() > 0)
			result = true;
		return result;

	}*/

	@Override
	public Admin findAdminById(int adminId)throws NoSuchAdminException 
{
		if (repository.existsById(adminId)) {
			return repository.findById(adminId).get();
		}
		throw new NoSuchAdminException("Admin with id"+adminId+"not found");

	}

	@Override
	public List<Admin> findAllAdmins() {

		return repository.findAll();
	}

	@Override
	public List<Admin> findAllAdminByName(String firstName)throws NoSuchAdminException
	{
		/*if(repository.)
		{
			throw new NoSuchAdminException("Admin with first Name "+firstName+"not found");
		}*/
		return repository.readAllName(firstName);
	}

	@Override
	public boolean loginAdmin(Admin admin) {
		boolean result = false;
		Admin admin2 = repository.readByEmailAndPassword(admin.getEmail(), admin.getPassword());
		if(admin2!=null) {
			result = true;
		}
		return result;
		
	}
	

	@Override
	public void deleteAdmin(int adminId)throws NoSuchAdminException
	{
		if (repository.existsById(adminId)) {
	repository.deleteById(adminId);
		}
		throw new NoSuchAdminException("Admin with id"+adminId+"not found");

	}

	@Override
	@Autowired
	public void MailService(JavaMailSender javaMailSender) {
	
		this.javaMailSender = javaMailSender;
	}

	@Override
	public void sendEmail(Admin admin) throws MailException, MessagingException
	{
	//	String email1=admin.getEmail();
		
		//SimpleMailMessage mail = new SimpleMailMessage();
		String result1=admin.getPassword();
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
		helper.setTo(admin.getEmail());
		helper.setSubject(" Automation Travel Agency");
		helper.setText("Your password is :");
		helper.setText("Your password is "+result1);
		
		javaMailSender.send(mimeMessage);
	}
	
	@Override
	public boolean updateAdmin(Admin admin) {
		repository.updateCustomer(admin.getFirstName(), admin.getLastName(), admin.getDateOfBirth(), admin.getMobileNo(),admin.getEmail(), admin.getPassword(),  admin.getAdminId());
		return true;
		
	}

	@Override
	public Admin findAdminByEmail(String email) {
		
		return repository.readByEmail(email);
	}

/*	@Override
	public void sendEmail(Admin admin) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(admin.getEmail());
		mail.setSubject(" Automation Travel Agency");
		mail.setText("heeloodsd");

		
		javaMailSender.send(mail);
		
	}*/
}
