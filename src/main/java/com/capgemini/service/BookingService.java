package com.capgemini.service;

import java.time.LocalDate;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import com.capgemini.exceptions.NoCapacityInVehicleException;
import com.capgemini.exceptions.NoSuchBookingIdFoundException;
import com.capgemini.exceptions.VehicleAlreadyDepartedException;
import com.capgemini.model.Booking;
import com.capgemini.model.Vehicle;

public interface BookingService {

	public void MailService(JavaMailSender javaMailSender);
	
	public Booking findBookingById(int bookingId);

	public Booking bookVehicle(Booking booking) throws NoCapacityInVehicleException,VehicleAlreadyDepartedException ,MailException, MessagingException;

	public void successfulBookingMail(Booking booking) throws MessagingException;
	
	public List<Vehicle> searchByLocation(String source, String destination, LocalDate journeyDate);

	public int cancelBooking(int bookingId) throws NoSuchBookingIdFoundException;

	public List<Booking> viewBookingStatus(int customerId);

	public List<Booking> viewAllBooking();
}