package com.capgemini.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("address")
@Scope("prototype")
@Entity
@Table(name = "ADDRESS_MASTER")
public class Address implements Serializable{
	@Id
	@Column(name = "ADDRESS_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;
	
	@Column(name = "CITY", length = 60)
	@NotEmpty(message = "City name Should not be Empty")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Please enter valid City name")
	@Size(min = 3,message = "City name should have at least 3 characters")
	private String city;
	
	@NotEmpty(message = "Pincode Should not be Empty")
	@Pattern(regexp = "^[0-9]+$", message = "Please enter valid Pincode")
	@Size(min = 6,max = 6,message = "Pincode should only have 6 numbers")
	@Column(name = "PINCODE", length = 6)
	private String pincode;
	
	public Address() {
		
	}
	
	public Address(int addressId, String city, String pincode) {
		super();
		this.addressId = addressId;
		this.city = city;
		this.pincode = pincode;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", city=" + city + ", pincode=" + pincode + "]";
	}
	
	
}
