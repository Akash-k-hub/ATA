package com.capgemini.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.exceptions.NoSuchRouteException;
import com.capgemini.model.Route;
import com.capgemini.repository.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteRepository repository;

	/*-----Creating Operation------*/
	@Override
	@Transactional
	public Route addRoute(Route route) {
		route.setStatus(true);
		route = repository.save(route);
		return route;
	}

	/*----- Reading Operation------*/
	@Override
	public Route findRouteById(int routeId) throws NoSuchRouteException {
		if (repository.existsById(routeId))
			return repository.findById(routeId).get();
		else
			throw new NoSuchRouteException("Route id " + routeId + " does not exist");
	}

	@Override
	public List<Route> findAllRoute() {
		return repository.findAll();
	}

//	/*------- Update Operation-------*/
//	@Override
//	public int modifyRouteBySource(String source, int routeId) throws NoSuchRouteException {
//		if (repository.existsById(routeId)) {
//			return repository.updateRouteSource(source, routeId);
//		}
//		throw new NoSuchRouteException("Route id " + routeId + " does not exist");
//	}
//
//	@Override
//	public int modifyRouteByDestination(String destination, int routeId) throws NoSuchRouteException {
//		if (repository.existsById(routeId)) {
//			return repository.updateRouteDestination(destination, routeId);
//		}
//		throw new NoSuchRouteException("Route id " + routeId + " does not exist");
//	}
//
//	@Override
//	public int modifyRouteByDistance(int routeId, double distance) throws NoSuchRouteException {
//		if (repository.existsById(routeId)) {
//			return repository.updateRouteDistance(distance, routeId);
//		}
//		throw new NoSuchRouteException("Route id " + routeId + " does not exist");
//	}
//
//	@Override
//	public int modifyRouteByDuration(int routeId, double duration) throws NoSuchRouteException {
//		if (repository.existsById(routeId)) {
//			return repository.updateRouteDuration(duration, routeId);
//		}
//		throw new NoSuchRouteException("Route id " + routeId + " does not exist");
//	}

	/*------- Delete Operation-----*/
	@Override
	public boolean removeRouteById(int routeId) throws NoSuchRouteException {
		if (repository.existsById(routeId)) {
			repository.deleteRouteById(routeId);
			return true;
		}
		throw new NoSuchRouteException("Route id " + routeId + " does not exist");
	}
	@Override
	public boolean modifyVehicleRoute(Route route) {
		repository.modifyVehicleRoute1(route.getSource(), route.getDestination(), route.getDistance(), route.getDuration(),route.getRouteId());
	
		return true;
	}
}
