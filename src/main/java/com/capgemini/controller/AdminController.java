package com.capgemini.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exceptions.NoSuchAddressException;
import com.capgemini.exceptions.NoSuchDriverException;
import com.capgemini.exceptions.NoSuchRouteException;
import com.capgemini.exceptions.NoSuchVehicleException;
import com.capgemini.model.Address;
import com.capgemini.model.Driver;
import com.capgemini.model.Route;
import com.capgemini.model.Vehicle;
import com.capgemini.service.AddressService;
import com.capgemini.service.DriverService;
import com.capgemini.service.RouteService;
import com.capgemini.service.VehicleService;

@RestController
@CrossOrigin
@RequestMapping(path = "admin")
public class AdminController {
	Logger logger = LoggerFactory.getLogger(AdminController.class);

	

	// ------------------------------ VEHICLE CONTROLLER
	// START-------------------------------------------------
	@Autowired
	private VehicleService vehicleService;
	
	// http://localhost:9090/user-api/admin/vehicle/ -POST
	@PostMapping(path = "vehicle/")
	public ResponseEntity<String> saveVehicle(@Valid @RequestBody Vehicle vehicle)  throws NoSuchRouteException, NoSuchDriverException{
		logger.info("Save Vehile method is accessed");
		ResponseEntity<String> response = null;
		Vehicle result = vehicleService.addVehicle(vehicle);
		if (result != null)
			response = new ResponseEntity<String>("Vehicle with id " + vehicle.getVehicleId() + " is added",
					HttpStatus.CREATED);
		logger.info("Vehicle is added");
		return response;
	}

	// http://localhost:9090/user-api/admin/searchByVehicleId/1 -GET
	@GetMapping(path = "searchByVehicleId/{vehicleId}")
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable("vehicleId") int vehicleId)
			throws NoSuchVehicleException {
		ResponseEntity<Vehicle> response = null;
		Vehicle vehicle = vehicleService.findVehicleById(vehicleId);
		response = new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
		logger.info("Search by VehicleId is displayed");
		return response;
	}

	// http://localhost:9090/user-api/admin/vehicles/ -GET
	@GetMapping(path = "vehicles/")
	public ResponseEntity<List<Vehicle>> getAllVehicles() {
		ResponseEntity<List<Vehicle>> response;
		List<Vehicle> list = vehicleService.findAllVehicle();
		response = new ResponseEntity<List<Vehicle>>(list, HttpStatus.OK);
		logger.info("All Vehicle data is fetched");
		return response;
	}

	// http://localhost:9090/user-api/admin/updateVehicle -PUT
	@PutMapping(path = "updateVehicle")
	public ResponseEntity<String> updateVehicle(@RequestBody Vehicle vehicle)
			throws NoSuchVehicleException {
		ResponseEntity<String> response = null;
		Boolean result = vehicleService.modifyVehicleById(vehicle);
		response = new ResponseEntity<String>("Vehicle with id " + vehicle.getVehicleId() + " is updated.",
				HttpStatus.CREATED);
		logger.info("Vehicle Data is Updated");
		return response;
	}

	// http://localhost:9090/user-api/admin/deleteVehicleById/1		-PUT_for_DELETE
	@PutMapping(path = "deleteVehicleById/{vehicleId}")
	public ResponseEntity<String> deleteVehicleById(@PathVariable("vehicleId") int vehicleId)
			throws NoSuchVehicleException {
		ResponseEntity<String> response = null;
		boolean result = vehicleService.removeVehicleById(vehicleId);
		if (result)
			response = new ResponseEntity<String>("Vehicle is deleted.", HttpStatus.OK);
		logger.warn("Vehicle Id is deleted");
		return response;
	}


//	VEHICLE CONTROLLER ENDS -------------------------------------------------------------------------------------------

//	DRIVER CONTROLLER STARTS-------------------------------------------------------------------------------------------

	@Autowired
	private DriverService service2;

	@Autowired
	private AddressService addressService;

	// http://localhost:9090/user-api/admin/saveDriver -POST
	@PostMapping(path = "saveDriver")
	public ResponseEntity<String> saveDriver(@Valid @RequestBody Driver driver) throws NoSuchAddressException {
		logger.info("Save Driver method is accessed");
		ResponseEntity<String> response = null;
		Driver result = service2.addDriver(driver);
		if (result != null)
			response = new ResponseEntity<String>("Driver with id " + driver.getDriverId() + " is added.",
					HttpStatus.CREATED);
		logger.info("Driver is added");
		return response;
	}

	// http://localhost:9090/user-api/admin/searchByDriverId/1 -GET
	@GetMapping(path = "searchByDriverId/{driverId}")
	public ResponseEntity<Driver> getDriverById(@PathVariable("driverId") int driverId) throws NoSuchDriverException {
		logger.info("Searching for Driver using Id");
		ResponseEntity<Driver> response = null;
		Driver driver = service2.findDriverById(driverId);
		response = new ResponseEntity<Driver>(driver, HttpStatus.OK);
		logger.info("Searching for Driver using Id");
		return response;
	}

	// http://localhost:9090/user-api/admin/getAllDrivers -GET
	@GetMapping(path = "getAllDrivers")
	public ResponseEntity<List<Driver>> getAllDrivers() {
		logger.info("All Drivers are displayed");
		ResponseEntity<List<Driver>> response = null;
		List<Driver> list = service2.findAllDriver();
		response = new ResponseEntity<List<Driver>>(list, HttpStatus.OK);
		logger.info("Getting All Drivers");
		return response;
	}

//	// http://localhost:9090/user-api/admin/updateDriverName/2/Ram
//	@PutMapping(path = "updateDriverName/{driverId}/{driverName}")
//	public ResponseEntity<String> updateDriverName(@PathVariable("driverName") String driverName,
//			@PathVariable("driverId") int driverId) throws NoSuchDriverException {
//		ResponseEntity<String> response = null;
//		int result = service2.updateDriverName(driverId, driverName);
//		if (result > 0)
//			response = new ResponseEntity<String>("Driver Name with id " + driverId + " is updated", HttpStatus.OK);
//		logger.info("Driver Name is updated");
//		return response;
//	}
//
//	
//
//	// http://localhost:9090/user-api/admin/updateDriverContactNo/2/8106762539
//	@PutMapping(path = "updateDriverContactNo/{driverId}/{contactNo}")
//	public ResponseEntity<String> updateDriverContactNo(@PathVariable("contactNo") String contactNo,
//			@PathVariable("driverId") int driverId) throws NoSuchDriverException {
//		ResponseEntity<String> response = null;
//		// Driver driver = null;
//		int result = service2.updateDriverContactNo(driverId, contactNo);
//		if (result > 0)
//			response = new ResponseEntity<String>("Driver ContactNo with id " + driverId + " is updated",
//					HttpStatus.OK);
//		logger.info("Driver Contact No is Updated");
//		return response;
//	}
//
//	// http://localhost:9090/user-api/admin/updateDriverLicenceNo/2/236781
//	@PutMapping(path = "updateDriverLicenceNo/{driverId}/{licenceNo}")
//	public ResponseEntity<String> updateDriverLicenceNo(@PathVariable("licenceNo") String licenceNo,
//			@PathVariable("driverId") int driverId) throws NoSuchDriverException {
//		ResponseEntity<String> response = null;
//		// Driver driver = null;
//		int result = service2.updateDriverLicenceNo(driverId, licenceNo);
//		if (result > 0)
//			response = new ResponseEntity<String>("Driver LicenceNo with id " + driverId + " is updated",
//					HttpStatus.OK);
//		logger.info("Driver Licence No is Updated");
//		return response;
//	}

	// http://localhost:9090/user-api/admin/deleteDriver/1
	@PutMapping(path = "deleteDriver/{driverId}")
	public ResponseEntity<String> deleteDriver(@PathVariable("driverId") int driverId) throws NoSuchDriverException {
		ResponseEntity<String> response = null;
		boolean result = service2.removeDriverById(driverId);
		if (result)
			response = new ResponseEntity<String>("Driver is deleted.", HttpStatus.OK);
		logger.warn("Driver is deleted");
		return response;
	}
	// http://localhost:9090/user-api/admin/update/
			@PutMapping(path="update/")
			public ResponseEntity<String> updateDriver(@RequestBody Driver driver) {
				ResponseEntity<String> response = null;
				boolean result = service2.updateDriver1(driver);
				if (result)
					response = new ResponseEntity<String>("Driver with id" + driver.getDriverId() + "is updated",
							HttpStatus.CREATED);
				return response;
			}

	// http://localhost:9090/user-api/admin/saveAddress - POST
	@PostMapping(path = "saveAddress")
	public ResponseEntity<String> saveAddress(@Valid @RequestBody Address address) {
		ResponseEntity<String> response = null;
		Address result = addressService.addAddress(address);
		if (result != null)
			response = new ResponseEntity<String>("Address with id " + address.getAddressId() + " is added.",
					HttpStatus.CREATED);
		logger.info("Address is saved");
		return response;
	}

	// http://localhost:9090/user-api/admin/getAllAddress -GET
	@GetMapping(path = "getAllAddress")
	public ResponseEntity<List<Address>> getAllAddress() {
		ResponseEntity<List<Address>> response = null;
		List<Address> list = addressService.findAllAddress();
		response = new ResponseEntity<List<Address>>(list, HttpStatus.OK);
		logger.info("All address are displayed");
		return response;
	}

	// http://localhost:9090/user-api/admin/updateCity/1/Hyderabad
	@PutMapping(path = "updateCity/{addressId}/{city}")
	public ResponseEntity<String> updateCity(@PathVariable("city") String city,
			@PathVariable("addressId") int addressId) throws NoSuchAddressException {
		ResponseEntity<String> response = null;
		// Driver driver = null;
		int result = addressService.updateCity(addressId, city);
		if (result > 0)
			response = new ResponseEntity<String>("Driver Name with id " + addressId + " is updated", HttpStatus.OK);
		logger.info("City is updated");
		return response;
	}
	// http://localhost:9090/user-api/admin/updatePincode/1/600001 - PUT
		@PutMapping(path = "updatePincode/{addressId}/{pincode}")
		public ResponseEntity<String> updatePincode(@PathVariable("pincode") String pincode,
				@PathVariable("addressId") int addressId) throws NoSuchAddressException {
			ResponseEntity<String> response = null;
			// Driver driver = null;
			int result = addressService.updatePincode(addressId, pincode);
			if (result > 0)
				response = new ResponseEntity<String>("Address with id " + addressId + " is updated", HttpStatus.OK);
			logger.info("Pincode is Updated");
			return response;
		}
	


//	DRIVER CONTROLLER ENDS----------------------------------------------------------------------------------------------------

//	ROUTE CONTROLLER STARTS---------------------------------------------------------------------------------------------------

		@Autowired
		private RouteService service3;

		// http://localhost:9090/user-api/admin/saveRoute/ -POST
		@PostMapping(path = "saveRoute/")
		public ResponseEntity<String> saveRoute(@Valid @RequestBody Route route)  {
			ResponseEntity<String> response = null;
			Route result = service3.addRoute(route);
			if (result != null)
				response = new ResponseEntity<String>("Route with id " + route.getRouteId() + " is added.",
						HttpStatus.CREATED);
			return response;
		}

		// http://localhost:9090/user-api/admin/searchByRouteId/1 -GET
		@GetMapping(path = "searchByRouteId/{routeId}")
		public ResponseEntity<Route> getRouteById(@PathVariable("routeId") int routeId) throws NoSuchRouteException {
			ResponseEntity<Route> response = null;
			Route route = service3.findRouteById(routeId);
			response = new ResponseEntity<Route>(route, HttpStatus.OK);
			return response;
		}

		// http://localhost:9090/user-api/admin/ -GET
		@GetMapping(path = "/")
		public ResponseEntity<List<Route>> getAllRoute() {
			ResponseEntity<List<Route>> response = null;
			List<Route> list = service3.findAllRoute();
			response = new ResponseEntity<List<Route>>(list, HttpStatus.OK);
			return response;
		}


//		// http://localhost:9090/user-api/admin/updateRouteSource/1/pune      -PUT
//		@PutMapping(path = "updateRouteSource/{routeId}/{source}")
//		public ResponseEntity<String> modifyRouteSource(@PathVariable("source") String source,
//				@PathVariable("routeId") int routeId) throws NoSuchRouteException {
//			ResponseEntity<String> response = null;
//			int result = service3.modifyRouteBySource(source, routeId);
//			if (result != 0)
//				response = new ResponseEntity<String>("Route with id " + routeId + " is updated", HttpStatus.OK);
//			return response;
//		}
//
//		// http://localhost:9090/user-api/admin/updateRouteDestination/1/pune    -PUT
//		@PutMapping(path = "updateRouteDestination/{routeId}/{destination}")
//		public ResponseEntity<String> modifyRouteDestination(@PathVariable("destination") String destination,
//				@PathVariable("routeId") int routeId) throws NoSuchRouteException {
//			ResponseEntity<String> response = null;
//			int result = service3.modifyRouteByDestination(destination, routeId);
//			if (result != 0)
//				response = new ResponseEntity<String>("Route with id " + routeId + " is updated", HttpStatus.OK);
//			return response;
//		}
//
//		// http://localhost:9090/user-api/admin/updateRouteDistance/1/149     -PUT
//		@PutMapping(path = "updateRouteDistance/{routeId}/{distance}")
//		public ResponseEntity<String> modifyRouteDistance(@PathVariable("distance") double distance,
//				@PathVariable("routeId") int routeId) throws NoSuchRouteException {
//			ResponseEntity<String> response = null;
//			int result = service3.modifyRouteByDistance(routeId, distance);
//			if (result != 0)
//				response = new ResponseEntity<String>("Route with id " + routeId + " is updated", HttpStatus.OK);
//			return response;
//		}
//
//		// http://localhost:9090/user-api/admin/updateRouteDuration/1/4     -PUT
//		@PutMapping(path = "updateRouteDuration/{routeId}/{duration}")
//		public ResponseEntity<String> modifyRouteDuration(@PathVariable("duration") double duration,
//				@PathVariable("routeId") int routeId) throws NoSuchRouteException {
//			ResponseEntity<String> response = null;
//			int result = service3.modifyRouteByDuration(routeId, duration);
//			if (result != 0)
//				response = new ResponseEntity<String>("Route with id " + routeId + " is updated", HttpStatus.OK);
//			return response;
//		}

		// http://localhost:9090/user-api/admin/deleteRoute/1   -DELETE
		@PutMapping(path = "deleteRoute/{routeId}")
		public ResponseEntity<String> deleteRoute(@RequestBody @PathVariable("routeId") int routeId) throws NoSuchRouteException {
			ResponseEntity<String> response = null;
			boolean result = service3.removeRouteById(routeId);
			if (result)
				response = new ResponseEntity<String>("Route with id "+routeId+" is deleted.", HttpStatus.OK);
			return response;
		}

//		http://localhost:9090/user-api/admin/modifyVehicleRoute     -PUT
				@PutMapping(path = "modifyVehicleRoute")
				public ResponseEntity<String> modifyVehicleRoute(@RequestBody Route route) {
					ResponseEntity<String> response = null;
					boolean result = service3.modifyVehicleRoute(route);
					if (result)
						response = new ResponseEntity<String>("Route with id " + route.getRouteId() + " is updated", HttpStatus.CREATED);
					return response;
				}
//	ROUTE CONTROLLER ENDS------------------------------------------------------------------------------------------------------------

}
