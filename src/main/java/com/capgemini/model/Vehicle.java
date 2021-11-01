package com.capgemini.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Scope(scopeName = "prototype")
@Component("vehicle")
@Table(name = "VEHICLE_MASTER")

public class Vehicle {
	
	public enum VehicleType {
		AC_SEATER, AC_SLEEPER, NON_AC_SEATER, NON_AC_SLEEPER;
	};
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VEHICLE_ID", nullable = false)
	private int vehicleId;

	@Column(name = "VEHICLE_NO", length = 15, nullable = false, unique = true)
	private String vehicleNo;

	@NotEmpty(message = "Please enter Vehicle Name!")
	@Pattern(regexp = "^[a-zA-Z &]+$", message = "Please enter valid name!")
	@Column(name = "VEHICLE_NAME", length = 30, nullable = false)
	private String vehicleName;

	@Column(name = "SITTING_CAPACITY", nullable = false)
	private int sittingCapacity;

	@Enumerated(EnumType.STRING)
	@Column(name = "VEHICLE_TYPE", length = 20, nullable = false)
	private VehicleType vehicleType;

	@Column(name = "FARE_PER_KM")
	@Min(value = 1, message = "please enter the fare/km")
	private int farePerKm;

	@Column(name = "JOURNEY_DATE")
	private LocalDate journeyDate;
	
	@Column(name="DEPART_TIME")
	private LocalTime departTime;
	
	@Column(name = "STATUS")
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Autowired
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ROUTE_ID")
	private Route route;

	@Autowired
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DRIVER_ID")
	private Driver driver;

	public Vehicle(Route route, Driver driver) {
		super();
		this.route = route;
		this.driver = driver;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Vehicle() {
		super();
	}

	public Vehicle(int vehicleId, String vehicleNo, String vehicleName, int sittingCapacity, VehicleType vehicleType,
			int farePerKm, LocalDate journeyDate,LocalTime departTime,boolean status) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleNo = vehicleNo;
		this.vehicleName = vehicleName;
		this.sittingCapacity = sittingCapacity;
		this.vehicleType = vehicleType;
		this.farePerKm = farePerKm;
		this.journeyDate = journeyDate;
		this.departTime=departTime;
		this.status = status;
	}

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public int getSittingCapacity() {
		return sittingCapacity;
	}

	public void setSittingCapacity(int sittingCapacity) {
		this.sittingCapacity = sittingCapacity;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getFarePerKm() {
		return farePerKm;
	}

	public void setFarePerKm(int farePerKm) {
		this.farePerKm = farePerKm;
	}

	public LocalTime getDepartTime() {
		return departTime;
	}

	public void setDepartTime(LocalTime departTime) {
		this.departTime = departTime;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleNo=" + vehicleNo + ", vehicleName=" + vehicleName
				+ ", sittingCapacity=" + sittingCapacity + ", vehicleType=" + vehicleType + ", farePerKm=" + farePerKm
				+ ", journeyDate=" + journeyDate + ", status=" + status + "]";
	}

}