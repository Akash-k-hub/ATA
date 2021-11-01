package com.capgemini.service;

import java.util.List;

import com.capgemini.exceptions.NoSuchRouteException;
import com.capgemini.model.Route;

public interface RouteService {
	public Route addRoute(Route route) ;

	public Route findRouteById(int routeId) throws NoSuchRouteException;

	public List<Route> findAllRoute();

//	public int modifyRouteBySource(String source, int routeId) throws NoSuchRouteException;
//
//	public int modifyRouteByDestination(String destination, int routeId) throws NoSuchRouteException;
//
//	public int modifyRouteByDistance(int routeId, double distance) throws NoSuchRouteException;
//
//	public int modifyRouteByDuration(int routeId, double duration) throws NoSuchRouteException;

	public boolean removeRouteById(int routeId) throws NoSuchRouteException ;
	
	public boolean modifyVehicleRoute(Route route);
}
