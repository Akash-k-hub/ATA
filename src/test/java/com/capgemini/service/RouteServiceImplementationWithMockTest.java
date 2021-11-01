package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.tomcat.jni.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import com.capgemini.exceptions.NoSuchRouteException;
import com.capgemini.model.Route;
import com.capgemini.repository.RouteRepository;

@SpringBootTest
class RouteServiceImplementationWithMockTest {
	
	@Autowired
	private RouteService service;
	
	@MockBean 
	private RouteRepository repository;
	
	
	@Test
	public void testFindAllRoutesShouldReturnRoutes() {
		when(repository.findAll()).thenReturn(Stream.of(new Route(100,"Delhi","Jaipur",280,5),new Route(10,"Agra","Jaipur",238,4)).collect(Collectors.toList()));
		assertEquals(2,service.findAllRoute().size());
	}
	
	@Test
	public void testAddRoutes() throws NoSuchRouteException {
		Route route = new Route(100,"Agra","Jaipur",238,4);
		when(repository.save(route)).thenReturn(route);
		assertEquals(route,service.addRoute(route));
	}
	@Test
	void testFindRouteByIdShouldThrownNoSuchDriverexception() throws NoSuchRouteException{
		int routeId = -1;
		when(repository.existsById(routeId)).thenReturn(false);
		assertThrows(NoSuchRouteException.class, () -> service.findRouteById(routeId));
	}
}
