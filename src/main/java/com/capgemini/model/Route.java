package com.capgemini.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("route")
@Scope(scopeName = "prototype")
@Entity
@Table(name = "ROUTE_MASTER")
public class Route implements Serializable {
	@Id
	@Column(name = "ROUTE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int routeId;

	@Column(name = "SOURCE", length = 30)
	@NotEmpty(message = "Please enter source")
	@Size(min = 3, message = "Please enter min 3 word")
	@Pattern(regexp = "[A-Za-z]+", message = "Please enter valid source")
	private String source;

	@Column(name = "DESTINATION", length = 30)
	@NotEmpty(message = "Please enter destination")
	@Size(min = 3, message = "Please enter min 3 word")
	@Pattern(regexp = "[A-Za-z]+", message = "Please enter valid destination")
	private String destination;

	@Column(name = "DISTANCE")
	@DecimalMin(value = "0.1", message = "Please enter valid distance")
	private double distance;

	@Column(name = "DURATION")
	@DecimalMin(value = "1", message = "Please enter valid duration")
	private double duration;
	
	@Column(name="STATUS")
	private boolean status;

	public Route() {

	}

	public Route(int routeId, String source, String destination, double distance, double duration) {
		super();
		this.routeId = routeId;
		this.source = source;
		this.destination = destination;
		this.distance = distance;
		this.duration = duration;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", source=" + source + ", destination=" + destination + ", distance="
				+ distance + ", duration=" + duration + "]";
	}

}
