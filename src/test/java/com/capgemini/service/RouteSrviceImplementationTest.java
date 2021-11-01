package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.capgemini.exceptions.NoSuchRouteException;
import com.capgemini.model.Route;
import com.capgemini.repository.RouteRepository;

@SpringBootTest
class RouteSrviceImplementationTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private RouteService service;

	private RouteRepository repository;

	@Test
	public void testFindRouteByIdShouldReturnRoute() throws NoSuchRouteException {
		Route expected = context.getBean(Route.class);
		expected.setSource("Test");
		expected.setDestination("Test");
		expected.setDistance(10.0);
		expected.setDuration(10.0);

		service.addRoute(expected);

		Route actual = service.findRouteById(expected.getRouteId());
		assertEquals(expected.getRouteId(), actual.getRouteId());
		assertEquals(expected.getSource(), actual.getSource());
		assertEquals(expected.getDestination(), actual.getDestination());
		assertEquals(expected.getDistance(), actual.getDistance());
		assertEquals(expected.getDuration(), actual.getDuration());

		service.removeRouteById(expected.getRouteId());
	}
	
	@Test
	public void testUpdateRouteBySourceShouldReturnUpdatedSource() throws NoSuchRouteException {
	Route route= new  Route(1,"Mumbai","Lonavala",87,2);
	route= service.findRouteById(1);
	//int result= service.modifyRouteBySource("Lonavala",1 );
	assertEquals("Lonavala",route.getSource());
	}
	@Test
	public void testUpdateRouteByDestinationShouldReturnUpdatedDestination() throws NoSuchRouteException {
	Route route= new  Route(1,"Mumbai","Lonavala",87,2);
	route= service.findRouteById(1);
	//int result= service.modifyRouteByDestination("Pune",1 );
	assertEquals("Pune",route.getDestination());
	}
	@Test
	public void testUpdateRouteByDistanceShouldReturnUpdatedDistance() throws NoSuchRouteException {
	Route route= new  Route(1,"Mumbai","Lonavala",87,2);
	route= service.findRouteById(1);
	//int result= service.modifyRouteByDistance(1,57 );
	assertEquals(57,route.getDistance());
	}
	@Test
	public void testUpdateRouteByDurationShouldReturnUpdatedDuration() throws NoSuchRouteException {
	Route route= new  Route(1,"Mumbai","Lonavala",87,2);
	route= service.findRouteById(1);
	//int result= service.modifyRouteByDuration(1,3 );
	assertEquals(3,route.getDuration());
	}


}
