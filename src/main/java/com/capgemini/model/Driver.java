package com.capgemini.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("driver")
@Scope("prototype")
@Entity
@Table(name = "DRIVER_MASTER")
public class Driver implements Serializable {
	
	@Id
	@Column(name = "DRIVER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int driverId;
	
	
	@Column(name = "DRIVER_NAME")
	@NotEmpty(message = "Driver name Should not be Empty")
	@Pattern(regexp = "^([a-zA-Z]+ ?)*$", message = "Please enter valid name")
	@Size(min = 2,message = "Driver name should have at least 2 characters")
	private String driverName;
	
	
	@Column(name = "CONTACT_NO", unique = true)
	@NotEmpty(message = "Driver ContactNo Should not be Empty")
	@Pattern(regexp = "^[0-9]+$", message = "Please enter valid contact number")
	@Size(min = 10, max = 10,message = "Driver ContactNo should only have 10 numbers")
	private String contactNo;
	
	@Column(name = "LICENCE_NO", unique = true)
	@NotEmpty(message = "Driver LicenceNo Should not be Empty")
	@Pattern(regexp = "^[A-Z]{2}[0-9]+$", message = "Please enter valid licence number")
	@Size(min = 10,max = 10, message = "Driver LicenceNo should only have 10 numbers")
	private String licenceNo;
	
	@Column(name = "STATUS")
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID")
	@Autowired
	private Address driverAddress;
	
	public Address getDriverAddress() {
		return driverAddress;
	}

	public void setDriverAddress(Address driverAddress) {
		this.driverAddress = driverAddress;
	}
	
	public Driver() {
		
	}
	public Driver(int driverId, String driverName, String contactNo, String licenceNo, Address driverAddress) {
		super();
		this.driverId = driverId;
		this.driverName = driverName;
		this.contactNo = contactNo;
		this.licenceNo = licenceNo;
		this.driverAddress = driverAddress;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", driverName=" + driverName + ", contactNo=" + contactNo
				+ ", licenceNo=" + licenceNo + ", driverAddress=" + driverAddress + "]";
	}

	
		
}
