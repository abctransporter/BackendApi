package com.eagleshipperapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.eagleshipperapi.bean.Bid;
import com.eagleshipperapi.bean.Lead;
@Service
public class BidService {
	
	String TAG = "Bid";
	
	public Bid createBid(Bid bid) throws InterruptedException, ExecutionException {
		Firestore fireStore=FirestoreClient.getFirestore();
		String bidId = fireStore.collection("Bid").document().getId().toString();	
	    bid.setBidId(bidId);
	    fireStore.collection("Bid").document(bidId).set(bid);
	    Lead lead = fireStore.collection("Lead").document(bid.getLeadId()).get().get().toObject(Lead.class);
	    int bidCount =lead.getBidCount()+1;
	    lead.setBidCount(bidCount);
	    fireStore.collection("Lead").document(bid.getLeadId()).set(lead);
	    return bid;
		
	}
	
	public Bid deleteBid(String bidId) throws InterruptedException, ExecutionException {
		Firestore fireStore=FirestoreClient.getFirestore();
		  Bid bid = fireStore.collection("Bid").document(bidId).get().get().toObject(Bid.class);
		  fireStore.collection("Bid").document(bidId).delete();
		  Lead lead = fireStore.collection("Lead").document(bid.getLeadId()).get().get().toObject(Lead.class);
		    int bidCount =lead.getBidCount()-1;
		    lead.setBidCount(bidCount);
		    fireStore.collection("Lead").document(bid.getLeadId()).set(lead);
	      return bid;
	 }
  
	public ArrayList<Bid>getAllBidsByLeadId(String id) throws InterruptedException, ExecutionException{
		Firestore fireStore=FirestoreClient.getFirestore(); 
		ArrayList<Bid>al=new ArrayList<Bid>();
		 ApiFuture<QuerySnapshot> future=fireStore.collection("Bid").whereEqualTo("leadId",id).get();
		 List<QueryDocumentSnapshot>documents; 
		 documents = future.get().getDocuments();
		   for (QueryDocumentSnapshot document : documents) {
			   al.add(document.toObject(Bid.class));
			}
	  return al;
   }
  
	public ArrayList<Bid>getAllBidsByTransporterId(String id) throws InterruptedException, ExecutionException{
		Firestore fireStore=FirestoreClient.getFirestore();  
		ArrayList<Bid>al=new ArrayList<Bid>();
		  ApiFuture<QuerySnapshot> future=fireStore.collection("Bid").whereEqualTo("transporterId",id).get();
		  List<QueryDocumentSnapshot>documents;
		  documents = future.get().getDocuments();
			for (QueryDocumentSnapshot document : documents) {
				   al.add(document.toObject(Bid.class));
				}
		 return al;
	  }   
	
	//get Pending bids by transporterId
	public ArrayList<Bid>getAllBidsByTransporterIdPending(String transporterId) throws InterruptedException, ExecutionException{
		Firestore fireStore=FirestoreClient.getFirestore();  
		ArrayList<Bid>al=new ArrayList<Bid>();
		  List<QueryDocumentSnapshot>documents =fireStore.collection("Bid").whereEqualTo("transporterId",transporterId).get().get().getDocuments();
			for (QueryDocumentSnapshot document : documents) {
				Bid bid = document.toObject(Bid.class);
				al.add(bid);
			}
		 return al;
	  }
	
	//delete all bid by lead Id
	public ArrayList<Bid> deleteBidsOfLead(String leadId) throws InterruptedException, ExecutionException{
		Firestore fireStore=FirestoreClient.getFirestore();  
		ArrayList<Bid>al=new ArrayList<Bid>();
		  List<QueryDocumentSnapshot>documents =fireStore.collection("Bid").whereEqualTo("leadId",leadId).get().get().getDocuments();
			for (QueryDocumentSnapshot document : documents) {
				Bid bid = document.toObject(Bid.class);
				al.add(bid);
				fireStore.collection(TAG).document(bid.getBidId()).delete();
			}
		 return al;
	  }
	
}