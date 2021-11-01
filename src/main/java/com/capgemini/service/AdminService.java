package com.capgemini.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import com.capgemini.exceptions.NoSuchAdminException;
import com.capgemini.model.Admin;

public interface AdminService {
	public Admin registration(Admin admin)throws NoSuchAdminException;

	public Admin findAdminById(int adminId)throws NoSuchAdminException;

	public List<Admin> findAllAdmins();

	public List<Admin> findAllAdminByName(String firstName)throws NoSuchAdminException;

	public void deleteAdmin(int adminId)throws NoSuchAdminException;

	public boolean loginAdmin(Admin admin);
	
	public void MailService(JavaMailSender javaMailSender);
	
	public void sendEmail(Admin admin)throws MailException, MessagingException;
	
	public boolean updateAdmin(Admin admin);
	
	public Admin findAdminByEmail(String email);

}
