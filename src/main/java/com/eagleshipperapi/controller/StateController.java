package com.eagleshipperapi.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.eagleshipperapi.bean.States;
import com.eagleshipperapi.exception.ResourceNotFoundException;
import com.eagleshipperapi.service.StateService;

@RestController
@RequestMapping("/states")
public class StateController {
	@Autowired
	StateService stateService;
	@GetMapping("/")
	public ResponseEntity<ArrayList<States>> getstateList() throws ExecutionException, InterruptedException {
		ArrayList<States> stateList = stateService.getStateList();
		return new ResponseEntity<ArrayList<States>>(stateList, HttpStatus.OK);
	}
	@PostMapping("/")
	public ResponseEntity<?> saveState(@RequestBody States state ) throws Exception {
		
	    States states=stateService.saveState(state);
		return new ResponseEntity<>(states,HttpStatus.OK);
	}
	@GetMapping("/{stateId}")
	  public ResponseEntity<?> getStateById(@PathVariable("stateId") String stateId) throws InterruptedException, ExecutionException, ResourceNotFoundException{
	     States s = stateService.getStateById(stateId);	  
	     if(s == null)
	    	 throw new ResourceNotFoundException("State not found");
	     else
	    	 return new ResponseEntity<States>(s,HttpStatus.OK);
	  }
}