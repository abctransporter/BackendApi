package com.eagleshipperapi.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.eagleshipperapi.service.BidService;
import com.eagleshipperapi.bean.Bid;
import com.eagleshipperapi.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/bid")
public class BidController {
	@Autowired
	BidService bidService;
	
	@PostMapping("/")
	public ResponseEntity<Bid> createBid(@RequestBody Bid bid) throws ResourceNotFoundException, InterruptedException, ExecutionException{
		if(bid == null)
			throw new ResourceNotFoundException("Bid Not found");
		return new ResponseEntity<Bid>(bidService.createBid(bid),HttpStatus.OK);
	}
	@DeleteMapping("/{bidId}")
	public ResponseEntity<Bid> deleteBid(@PathVariable String bidId) throws ResourceNotFoundException, InterruptedException, ExecutionException{
		return new ResponseEntity<Bid>(bidService.deleteBid(bidId),HttpStatus.OK);
	}

	@GetMapping("/{leadId}")
    public ResponseEntity<?> getAllBidsByLeadId(@PathVariable("leadId")String id) throws InterruptedException, ExecutionException{
	ArrayList<Bid>al=bidService.getAllBidsByLeadId(id);
	return new ResponseEntity<ArrayList<Bid>>(al,HttpStatus.OK);
   }
  
	@GetMapping("/transporter/{transporterId}")
    public ResponseEntity<?> getAllBidsByTransporterId(@PathVariable("transporterId")String id) throws InterruptedException, ExecutionException{
	ArrayList<Bid>al=bidService.getAllBidsByTransporterId(id);
	return new ResponseEntity<ArrayList<Bid>>(al,HttpStatus.OK);
   }

	//get Pending bids
	@GetMapping("/transporter/pending/{transporterId}")
    public ResponseEntity<?> getAllPendingBidsByTransporterId(@PathVariable("transporterId")String transporterId) throws InterruptedException, ExecutionException{
	ArrayList<Bid>al=bidService.getAllBidsByTransporterIdPending(transporterId);
	return new ResponseEntity<ArrayList<Bid>>(al,HttpStatus.OK);
    }

	@DeleteMapping("/allbids/{leadId}")
	
	public ResponseEntity<?> deleteAllBidsByLeadId(@PathVariable("leadId")String leadId) throws InterruptedException, ExecutionException{
		ArrayList<Bid>al=bidService.deleteBidsOfLead(leadId);
		return new ResponseEntity<ArrayList<Bid>>(al,HttpStatus.OK);
	   }
}
