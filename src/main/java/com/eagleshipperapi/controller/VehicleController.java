package com.eagleshipperapi.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eagleshipperapi.bean.Vehicle;
import com.eagleshipperapi.exception.ResourceNotFoundException;
import com.eagleshipperapi.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	@Autowired
	VehicleService vehicleService;
	
	@PostMapping("/")
	public Vehicle createVehicle(@RequestParam("name")String name,
			@RequestParam("count")int count,
			@RequestParam("transporterId")String id,
			@RequestParam("file")MultipartFile file) throws ResourceNotFoundException, IOException, InterruptedException, ExecutionException{
		if(file.isEmpty()) 
			throw new ResourceNotFoundException("File not found ");
		Vehicle vehicle = new Vehicle();
		vehicle.setName(name);
		vehicle.setCount(count);
		Vehicle v = vehicleService.createVehicle(file, vehicle, id);
		return v;
	}
	
	@DeleteMapping("/{vehicleId}/{transporterId}")
	public ResponseEntity<Vehicle> deleteVehicle(@PathVariable String vehicleId,@PathVariable String transporterId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Vehicle vehicle = vehicleService.deleteVehicle(vehicleId, transporterId);
		if(vehicle == null)
			throw new ResourceNotFoundException("Vehicle  not found");
		return new ResponseEntity<Vehicle>(vehicle,org.springframework.http.HttpStatus.OK);
	}
	

	@PostMapping("/{transporterId}")
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable String transporterId,@RequestBody Vehicle vehicle) throws InterruptedException, ExecutionException, ResourceNotFoundException, IOException {
		Vehicle v = vehicleService.updateVehicle(transporterId,vehicle);
		if(v== null)
			throw new ResourceNotFoundException("Vehicle  not found");
		return new ResponseEntity<Vehicle>(vehicle,org.springframework.http.HttpStatus.OK);
	}
}
