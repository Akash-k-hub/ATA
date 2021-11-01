package com.capgemini.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHadling extends ResponseEntityExceptionHandler {
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoCapacityInVehicleException.class)
	protected ErrorInformation handleNoCapacityException(NoCapacityInVehicleException ex, HttpServletRequest req) {
		String bodyOfResponse = ex.getMessage(); 
		String uri = req.getRequestURL().toString();
		return new ErrorInformation(uri, bodyOfResponse);
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	protected ErrorInformation handleNoSuchIdFoundException(NoSuchElementException ex, HttpServletRequest req) {
		String bodyOfResponse = ex.getMessage(); 
		String uri = req.getRequestURL().toString();
		return new ErrorInformation(uri, bodyOfResponse);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidDateException.class)
	protected ErrorInformation handleDateException(InvalidDateException ex, HttpServletRequest req) {
		String bodyOfResponse = ex.getMessage(); 
		String uri = req.getRequestURL().toString();
		return new ErrorInformation(uri, bodyOfResponse);
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoSuchAdminException.class)
	protected ErrorInformation handleAdminException(NoSuchAdminException ex, HttpServletRequest req) {
		String bodyOfResponse = ex.getMessage(); 
		String uri = req.getRequestURL().toString();
		return new ErrorInformation(uri, bodyOfResponse);
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoSuchCustomerException.class)
	protected ErrorInformation handleCustomerException(NoSuchCustomerException ex, HttpServletRequest req) {
		String bodyOfResponse = ex.getMessage(); 
		String uri = req.getRequestURL().toString();
		return new ErrorInformation(uri, bodyOfResponse);
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoSuchDriverException.class)
	protected ErrorInformation handleDriverException(NoSuchDriverException ex, HttpServletRequest req) {
		String bodyOfResponse = ex.getMessage(); 
		String uri = req.getRequestURL().toString();
		return new ErrorInformation(uri, bodyOfResponse);
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoSuchRouteException.class)
	protected ErrorInformation handleRouteException(NoSuchRouteException ex, HttpServletRequest req) {
		String bodyOfResponse = ex.getMessage(); 
		String uri = req.getRequestURL().toString();
		return new ErrorInformation(uri, bodyOfResponse);
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(VehicleAlreadyDepartedException.class)
	protected ErrorInformation handleVehicleDepartedException(VehicleAlreadyDepartedException ex, HttpServletRequest req) {
		String bodyOfResponse = ex.getMessage(); 
		String uri = req.getRequestURL().toString();
		return new ErrorInformation(uri, bodyOfResponse);
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoSuchBookingIdFoundException.class)
	protected ErrorInformation handleBookingIdException(NoSuchBookingIdFoundException ex, HttpServletRequest req) {
		String bodyOfResponse = ex.getMessage(); 
		String uri = req.getRequestURL().toString();
		return new ErrorInformation(uri, bodyOfResponse);
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoSuchVehicleException.class)
	protected ErrorInformation handleBookingIdException(NoSuchVehicleException ex, HttpServletRequest req) {
		String bodyOfResponse = ex.getMessage(); 
		String uri = req.getRequestURL().toString();
		return new ErrorInformation(uri, bodyOfResponse);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
}