	package com.eagleshipperapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eagleshipperapi.bean.Lead;
import com.eagleshipperapi.bean.State;
import com.eagleshipperapi.bean.States;
import com.eagleshipperapi.bean.User;
import com.eagleshipperapi.exception.ResourceNotFoundException;
import com.eagleshipperapi.service.LeadService;

import retrofit2.http.DELETE;

@RestController
@RequestMapping("/lead")
public class LeadController {

	@Autowired
	LeadService leadService;

	// create Lead
	@PostMapping("/")
	public ResponseEntity<Lead> createNewLead(@RequestBody Lead lead) {
		lead.setStatus("create");
		return new ResponseEntity<Lead>(leadService.createNewLead(lead), org.springframework.http.HttpStatus.OK);
	}

	// get All of single user Lead
	@GetMapping("/user/{userId}")
	public ResponseEntity<ArrayList<Lead>> getLeadByUserId(@PathVariable String userId)
			throws InterruptedException, ExecutionException {
		ArrayList<Lead> al = leadService.getLeadByUserId(userId);
		return new ResponseEntity<ArrayList<Lead>>(al, org.springframework.http.HttpStatus.OK);
	}

	// get Single lead by ID
	@GetMapping("/{leadId}")
	public ResponseEntity<Lead> getLead(@PathVariable String leadId)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Lead lead = leadService.getLeadByLeadId(leadId);
		if (lead != null)
			return new ResponseEntity<Lead>(lead, org.springframework.http.HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Lead not found");
	}    
	
	//get Created Load by userId
	@GetMapping("/createLead/{userId}")
	public ResponseEntity<ArrayList<Lead>> getCreatedLeadById(@PathVariable String userId) throws Exception{
		ArrayList<Lead>al = leadService.getCreatedLeadById(userId);
		return new ResponseEntity<ArrayList<Lead>>(al, org.springframework.http.HttpStatus.OK);
	}
	
	//get Confirmed Load by userId
		@GetMapping("/confirmLead/{userId}")
		public ResponseEntity<ArrayList<Lead>> getConfirmLeadById(@PathVariable String userId) throws Exception{
			ArrayList<Lead>al = leadService.getConfirmedLeadById(userId);
			if(al.size()!=0)
				return new ResponseEntity<ArrayList<Lead>>(al, org.springframework.http.HttpStatus.OK);
			else
				throw new ResourceNotFoundException("Confirm Lead not found");
		}
		
	//get Completed Lead by userId	
		@GetMapping("/completeLead/{userId}")
		public ResponseEntity<ArrayList<Lead>> getCompleteLeadById(@PathVariable String userId) throws Exception{
			ArrayList<Lead>al = leadService.getCompletedLeadById(userId);
			if(al.size()!=0)
				return new ResponseEntity<ArrayList<Lead>>(al, org.springframework.http.HttpStatus.OK);
			else
				throw new ResourceNotFoundException("Confirm Lead not found");
		}
		
	//get All Completed Leads by TransporterId	
		@GetMapping("/completedLead/transporterId/{transporterId}")
		public ResponseEntity<ArrayList<Lead>> getCompleteLeadByTransporterID(@PathVariable String transporterId) throws Exception{
			ArrayList<Lead>al = leadService.getCompletedLeadByTransporterId(transporterId);
			if(al.size()!=0)
				return new ResponseEntity<ArrayList<Lead>>(al, org.springframework.http.HttpStatus.OK);
			else
				throw new ResourceNotFoundException("Confirm Lead not found");
		}
		
	//get All Confirmed Leads By Transporter Id
		@GetMapping("/leadByTransporterId/{transporterId}")
		public ResponseEntity<ArrayList<Lead>> getCurrentLoadOfTransporter(@PathVariable String transporterId) throws Exception{
			ArrayList<Lead>al = leadService.getAllConfirmedLeadsOfUser(transporterId);
			if(al.size()!=0)
				return new ResponseEntity<ArrayList<Lead>>(al, org.springframework.http.HttpStatus.OK);
			else
				throw new ResourceNotFoundException("Confirm Lead not found");
		}
		
		
	//update Create Load by Id
		@PostMapping("/update/{leadId}")
		public ResponseEntity<Lead> updateLeadById(@PathVariable("leadId")String leadId, @RequestBody Lead lead) throws InterruptedException, ExecutionException, ResourceNotFoundException {
			Lead l = leadService.updateLeadByLeadId(leadId, lead);
			if(l!=null)
				return new ResponseEntity<Lead>(l, org.springframework.http.HttpStatus.OK);
			else
				throw new ResourceNotFoundException("Lead not found");
		}
		
		
		
	//get Filter load 
		@PostMapping("/filter/{transporterId}")
		public ResponseEntity<ArrayList<Lead>> getFilterdLoads(@PathVariable("transporterId")String transporterId , @RequestBody ArrayList<States> stateList) throws InterruptedException, ExecutionException, ResourceNotFoundException {
			ArrayList<Lead> al = leadService.getFilteredLeads(transporterId,stateList);
			if(al.size() != 0)
				return new ResponseEntity<ArrayList<Lead>>(al, org.springframework.http.HttpStatus.OK);
			else
				throw new ResourceNotFoundException("Match Result not found");
		}
		
	//get All created Load	
		@GetMapping("/filter/all/{transporterId}")
		public ResponseEntity<ArrayList<Lead>> getCreatedLoads(@PathVariable("transporterId")String transporterId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
			ArrayList<Lead> al = leadService.getCreatedLeads(transporterId);
			if(al.size() != 0)
				return new ResponseEntity<ArrayList<Lead>>(al, org.springframework.http.HttpStatus.OK);
			else
				throw new ResourceNotFoundException("Match Result not found");
		}
		
	//delete lead  by  load by id
		@DeleteMapping("/{leadId}")
		public ResponseEntity<Lead> deleteLeadById(@PathVariable("leadId")String leadId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
			Lead l = leadService.deleteLeadByLeadId(leadId);
			if(l!=null)
				return new ResponseEntity<Lead>(l, org.springframework.http.HttpStatus.OK);
			else
				throw new ResourceNotFoundException("Lead not found");
		}

}