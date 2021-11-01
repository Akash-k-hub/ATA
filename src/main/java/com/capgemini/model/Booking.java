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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("booking")
@Scope(scopeName = "prototype")
@Entity
@Table(name = "BOOKING_MASTER")
public class Booking implements Serializable {

	@Id
	@Column(name = "BOOKING_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;

	/*
	 * @Autowired
	 * 
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name="VEHICLE_ID")
	 */
	@Autowired
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	@Autowired
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@Column(name = "BOOKING_DATE")
	private LocalDate bookingDate;

	@NotEmpty(message = "Please enter Vehicle Name!")
	@Pattern(regexp = "^[a-zA-Z &]+$", message = "Please enter valid name!")
	@Column(name = "BOARDING_POINT")
	private String boardingPoint;

	@NotEmpty(message = "Please enter Vehicle Name!")
	@Pattern(regexp = "^[a-zA-Z &]+$", message = "Please enter valid name!")
	@Column(name = "DROP_POINT")
	private String dropPoint;

	@Range(min = 1, max = 5, message = "You can only book minimum 1 and maximum 5 sits only.")
	@Column(name = "NO_OF_PASSENGERS")
	private int noOfPassengers;

	@Column(name = "FARE")
	private double fare;
	
	@Column(name = "STATUS")
	private boolean status;

	public Booking(int bookingId, LocalDate bookingDate, String boardingPoint,
			String dropPoint, int noOfPassengers, double fare,boolean status) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.boardingPoint = boardingPoint;
		this.dropPoint = dropPoint;
		this.noOfPassengers = noOfPassengers;
		this.fare = fare;
		this.status = status;
	}

	public Booking() {
	}

	public Booking(Customer customer) {
		this.customer = customer;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBoardingPoint() {
		return boardingPoint;
	}

	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}

	public String getDropPoint() {
		return dropPoint;
	}

	public void setDropPoint(String dropPoint) {
		this.dropPoint = dropPoint;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", boardingPoint="
				+ boardingPoint + ", dropPoint=" + dropPoint + ", noOfPassengers=" + noOfPassengers + ", fare=" + fare
				+ ", status=" + status + "]";
	}
}