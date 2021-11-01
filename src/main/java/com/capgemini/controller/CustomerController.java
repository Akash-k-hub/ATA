package com.capgemini.controller;

import java.time.LocalDate;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exceptions.InvalidDateException;
import com.capgemini.exceptions.NoCapacityInVehicleException;
import com.capgemini.exceptions.NoSuchBookingIdFoundException;
import com.capgemini.exceptions.VehicleAlreadyDepartedException;
import com.capgemini.model.Booking;
import com.capgemini.model.Vehicle;
import com.capgemini.service.BookingService;

@RestController
@RequestMapping(path = "booking")
@CrossOrigin
public class CustomerController {
	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private BookingService bookingService;

	// http://localhost:9090/user-api/booking/
	@PostMapping(path = "/")
	public ResponseEntity<String> saveBooking(@Valid @RequestBody Booking booking)
			throws NoCapacityInVehicleException, MailException, MessagingException, VehicleAlreadyDepartedException {
		logger.info("Save Booking Method is accessed");
		ResponseEntity<String> response = null;
		Booking result = bookingService.bookVehicle(booking);
		
		if (result != null) {
			response = new ResponseEntity<String>("Booking with id " + booking.getBookingId() + " is added",
					HttpStatus.CREATED);
			logger.info("Booking is created");
		}
		return response;
	}

	// http://localhost:9090/user-api/booking/search/Amalner/Mumbai/2020-07-20
		@GetMapping("/search/{source}/{destination}/{journeyDate}")
		public ResponseEntity<List<Vehicle>> searchUserRequest(@PathVariable("source") String source,
				@PathVariable("destination") String destination,
				@PathVariable("journeyDate") @DateTimeFormat(iso = ISO.DATE) LocalDate journeyDate)
				throws InvalidDateException {

			if(source == null || destination == null || journeyDate == null) {
				throw new NullPointerException();
			}
			
			System.out.println(journeyDate);
			LocalDate todayDate = LocalDate.now();
//			LocalDate journeyDate = journeyDate.toLocalDate();
			int validDate = journeyDate.compareTo(todayDate);
			if (validDate < 0) {
				throw new InvalidDateException("Date is already passed. Please type valid date");
			}
			ResponseEntity<List<Vehicle>> response = null;
			List<Vehicle> list = bookingService.searchByLocation(source, destination, journeyDate);
			response = new ResponseEntity<List<Vehicle>>(list, HttpStatus.OK);
			logger.info("Searching using source, destination, journey date");
			return response;
		}

	// http://localhost:9090/user-api/booking/searchById/1
	@GetMapping(path = "/searchById/{customerId}")
	public ResponseEntity<List<Booking>> getBookingByCustomerId(@PathVariable("customerId") int customerId) {
		ResponseEntity<List<Booking>> response = null;
		List<Booking> bookingHistory = bookingService.viewBookingStatus(customerId);
		response = new ResponseEntity<List<Booking>>(bookingHistory, HttpStatus.OK);
		logger.info("Searching for Customer using Id");
		return response;
	}

	// http://localhost:9090/user-api/booking/
	@GetMapping(path = "/")
	public ResponseEntity<List<Booking>> getAllBooking() {
		ResponseEntity<List<Booking>> response = null;
		List<Booking> booking = bookingService.viewAllBooking();
		response = new ResponseEntity<List<Booking>>(booking, HttpStatus.OK);
		logger.info("Getting the list of bookings");
		return response;
	}

	// http://localhost:9090/user-api/booking/delete/1/
	@PutMapping(path = "delete/{bookingId}")
	public ResponseEntity<String> cancelBooking(@PathVariable("bookingId") int bookingId) throws NoSuchBookingIdFoundException {
		ResponseEntity<String> response = null;
		int result = bookingService.cancelBooking(bookingId);
		if (result != 0)
			response = new ResponseEntity<String>("Booking with id " + bookingId + " is deleted", HttpStatus.OK);
		logger.warn("Booking is deleted");
		return response;
	}
	// http://localhost:9090/user-api/booking/searchByBookingId/1 -GET
				@GetMapping(path = "searchByBookingId/{bookingId}")
				public ResponseEntity<Booking> getBookingById(@PathVariable("bookingId") int bookingId) {
					ResponseEntity<Booking> response = null;
					Booking booking = bookingService.findBookingById(bookingId);
					response = new ResponseEntity<Booking>(booking,HttpStatus.OK);
					return response;
				}
}