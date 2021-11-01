package com.capgemini.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("admin")
@Scope(scopeName = "prototype")
@Entity
@Table(name = "ADMIN_MASTER")


public class Admin implements Serializable {

	@Id
	@Column(name = "ADMIN_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;

	
	@Column(name = "FIRST_NAME", length = 15)
	@NotEmpty(message="Please enter First Name")
	@Size(min=2,message="First Name should have at least 2 characters")
	@Pattern(regexp="^[a-zA-Z]+$",message="Please Enter Valid Name")
	private String firstName;

	@Column(name = "LAST_NAME", length = 15)
	@NotEmpty(message="Please enter Last Name")
	@Size(min=2,message="Last Name should have at least 2 characters")
	@Pattern(regexp="^[a-zA-Z]+$",message="Please Enter Valid Name")
	private String lastName;
	
	
	@Column(name = "DATE_OF_BIRTH")
	private LocalDate dateOfBirth;
	
	
	
	//@NotEmpty
	//@Range(min=10,max=11)
	@Column(name = "MOBILE_NUMBER", length = 10,unique = true)
	
	//@Size( min=10,message="Mobile Number should be of 10 digits")
	//@Min(value = 10,message="Number should be minimum 10 digits")
	//@Max(value = 10,message="Number should be maximum 10 digits")
	private long mobileNo;
	
	@NotEmpty
	@Email
	@Column(name = "EMAIL", length = 40,unique = true)
	private String email;
	
	@NotEmpty
	@Size(min=8, message="Password should have at least 8 characters")
	@Column(name = "PASSWORD", length = 20)
	private String password;
	
	
	@Autowired
	@JoinColumn(name="ADDRESS_ID")
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	public Admin() {

	}

	public Admin(int adminId, String firstName, String lastName, LocalDate dateOfBirth, long mobileNo,
			String email, String password,Address address) {
		super();
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.mobileNo = mobileNo;
		this.email = email;
		this.password = password;
		this.address=address;
	}

	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", address=" + address + ", mobileNo=" + mobileNo + ", email=" + email + ", password="
				+ password + "]";
	}

}
