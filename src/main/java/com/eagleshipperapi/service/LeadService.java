package com.eagleshipperapi.service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.eagleshipperapi.bean.Bid;
import com.eagleshipperapi.bean.Lead;
import com.eagleshipperapi.bean.State;
import com.eagleshipperapi.bean.States;
import com.eagleshipperapi.bean.User;
import com.eagleshipperapi.exception.ResourceNotFoundException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class LeadService {
	
	private static final String TAG ="Lead";
	ArrayList<Lead> al = new ArrayList<Lead>();
	
	//create lead 
	public Lead createNewLead(Lead lead) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		String leadId = dbFirestore.collection(TAG).document().getId();
		lead.setLeadId(leadId);
		dbFirestore.collection(TAG).document(leadId).set(lead);
		return lead;
	}
	
	
	//get all lead by user id
		public ArrayList<Lead> getLeadByUserId(String userId) throws InterruptedException, ExecutionException{
			Firestore dbFirestore = FirestoreClient.getFirestore();
			
			List<QueryDocumentSnapshot>document = dbFirestore.collection(TAG).whereEqualTo("userId", userId).get().get().getDocuments();
			for(QueryDocumentSnapshot queryDocument : document ) {
				al.add(queryDocument.toObject(Lead.class));
			}
			Collections.sort(al,new Lead());
			return al;		
		}
	//get single lead by id
		public Lead getLeadByLeadId(String leadId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
			Firestore dbFirestore = FirestoreClient.getFirestore();
			Lead lead =  dbFirestore.collection(TAG).document(leadId).get().get().toObject(Lead.class);
		    return lead;
		}
		
	//get Created Load by userId
		public ArrayList<Lead> getCreatedLeadById(String userId) throws Exception, Exception{
			Firestore dbFirestore = FirestoreClient.getFirestore();
			ArrayList<Lead> al = new ArrayList<>();
			List<QueryDocumentSnapshot>document = dbFirestore.collection(TAG).whereEqualTo("userId", userId).get().get().getDocuments();
			for(QueryDocumentSnapshot queryDocument : document ) {
				Lead lead = queryDocument.toObject(Lead.class);
				if(lead.getStatus().equalsIgnoreCase("create"))
					al.add(lead);
			}
			Collections.sort(al,new Lead());
			return al;
		}
		
	//get Confirmed Load by userId	
		public ArrayList<Lead> getConfirmedLeadById(String userId) throws Exception, Exception{
			Firestore dbFirestore = FirestoreClient.getFirestore();
			ArrayList<Lead> al = new ArrayList<>();
			List<QueryDocumentSnapshot>document = dbFirestore.collection(TAG).whereEqualTo("userId", userId).get().get().getDocuments();
			for(QueryDocumentSnapshot queryDocument : document ) {
				Lead lead = queryDocument.toObject(Lead.class);
				if(lead.getStatus().equalsIgnoreCase("confirmed"))
					al.add(lead);
			}
			Collections.sort(al,new Lead());
			return al;
		}
		
	//get Completed Load By userId	
		public ArrayList<Lead> getCompletedLeadById(String userId) throws Exception, Exception{
			Firestore dbFirestore = FirestoreClient.getFirestore();
			ArrayList<Lead> al = new ArrayList<>();
			List<QueryDocumentSnapshot>document = dbFirestore.collection(TAG).whereEqualTo("userId", userId).get().get().getDocuments();
			for(QueryDocumentSnapshot queryDocument : document ) {
				Lead lead = queryDocument.toObject(Lead.class);
				if(lead.getStatus().equalsIgnoreCase("completed"))
					al.add(lead);
			}
			Collections.sort(al,new Lead());
			return al;
		}
		
	//get Completed load By TransporterId
		public ArrayList<Lead> getCompletedLeadByTransporterId(String transporterID) throws Exception, Exception{
			Firestore dbFirestore = FirestoreClient.getFirestore();
			ArrayList<Lead> al = new ArrayList<>();
			List<QueryDocumentSnapshot>document = dbFirestore.collection(TAG).whereEqualTo("dealLockedWith", transporterID).get().get().getDocuments();
			for(QueryDocumentSnapshot queryDocument : document ) {
				Lead lead = queryDocument.toObject(Lead.class);
				if(lead.getStatus().equalsIgnoreCase("completed"))
					al.add(lead);
			}
			Collections.sort(al,new Lead());
			return al;
		}
	
	//get Confirm Lead Of All User match With Transporter Id Lead
		public ArrayList<Lead> getAllConfirmedLeadsOfUser(String transporterId) throws InterruptedException, ExecutionException{
			Firestore dbFirestore = FirestoreClient.getFirestore();
			ArrayList<Lead> leadList = new ArrayList<>();
			List<QueryDocumentSnapshot> document = dbFirestore.collection(TAG).whereEqualTo("dealLockedWith", transporterId).get().get().getDocuments();
			for(QueryDocumentSnapshot ds : document) {
				Lead lead = ds.toObject(Lead.class);
				if(lead.getStatus().equalsIgnoreCase("confirmed"))
					leadList.add(lead);
			}
			Collections.sort(leadList,new Lead());
			return leadList;	
		}
		
		
	// update lead by LeadId
		public Lead updateLeadByLeadId(String leadId,Lead lead) throws InterruptedException, ExecutionException {
			Firestore dbFirestore = FirestoreClient.getFirestore();
			Lead l = dbFirestore.collection(TAG).document(leadId).get().get().toObject(Lead.class);
			if(lead.getBidCount()!=0)
				l.setBidCount(lead.getBidCount());
			
			if(lead.getContactForDelivery()!=null)
				l.setContactForDelivery(lead.getContactForDelivery());
			
			if(lead.getContactForPickup()!=null)
				l.setContactForPickup(lead.getContactForPickup());
			
			if(lead.getDateOfCompletion()!=null)
				l.setDateOfCompletion(lead.getDateOfCompletion());
			
			if(lead.getDealLockedWith()!=null) {
				l.setDealLockedWith(lead.getDealLockedWith());
				l.setTransporterName(lead.getTransporterName());
			}
			
			if(lead.getDeliveryAddress()!=null)
				l.setDeliveryAddress(lead.getDeliveryAddress());
			
			if(lead.getPickUpAddress()!=null)
				l.setPickUpAddress(lead.getPickUpAddress());
			
			l.setStatus(lead.getStatus());
			
			if(lead.getTypeOfMaterial()!=null)
				l.setTypeOfMaterial(lead.getTypeOfMaterial());
			
			
			l.setMaterialStatus(lead.getMaterialStatus());
			
			l.setAmount(lead.getAmount());
			
			l.setWeight(lead.getWeight());
			
			l.setRemark(lead.getRemark());
			dbFirestore.collection(TAG).document(leadId).set(l);
			return l;
		}
		
		
		//get Filterd data
		public ArrayList<Lead> getFilteredLeads(String transporterId ,ArrayList<States> stateList) throws InterruptedException, ExecutionException{
			Firestore dbFirestore = FirestoreClient.getFirestore();
			ArrayList<Lead> al = new ArrayList<>();
			boolean status = false;
			List<QueryDocumentSnapshot> query = dbFirestore.collection(TAG).get().get().getDocuments();
			for(States s : stateList) {
				for(QueryDocumentSnapshot ds : query) {
					Lead lead = ds.toObject(Lead.class);
					String[] pickup = lead.getPickUpAddress().split(",");
					if(pickup[2].equalsIgnoreCase(s.getStateName())) {
						if(lead.getStatus().equalsIgnoreCase("create")) {
							List<QueryDocumentSnapshot> bidQuery = dbFirestore.collection("Bid").whereEqualTo("leadId",lead.getLeadId()).get().get().getDocuments();
							for(QueryDocumentSnapshot bs : bidQuery) {
								Bid bid = bs.toObject(Bid.class);
								if(bid.getTransporterId().equals(transporterId)) {
									status = true;
									break;
								}
							}
							if(!status) {
								al.add(lead);
							}
							status = false;
						}
					}
				}
			}
			Collections.sort(al,new Lead());
			return al;
		}
		
		//get All created leads
		public ArrayList<Lead> getCreatedLeads(String transporterId) throws InterruptedException, ExecutionException{
			Firestore dbFirestore = FirestoreClient.getFirestore();
			
			ArrayList<Lead> al = new ArrayList<>();
		boolean status = false;
		List<QueryDocumentSnapshot> query = dbFirestore.collection(TAG).whereEqualTo("status","create").get().get().getDocuments();
		for(QueryDocumentSnapshot ds : query) {
			Lead lead = ds.toObject(Lead.class);
			long t = Calendar.getInstance().getTimeInMillis();
			
			String str_date = lead.getDateOfCompletion().replace('/', '-');
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			try {
				Date date = (Date)formatter.parse(str_date);
				if(t >= date.getTime()) {
					dbFirestore.collection(TAG).document(lead.getLeadId()).delete();				
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			if(lead!=null) {
				List<QueryDocumentSnapshot> bidQuery = dbFirestore.collection("Bid").whereEqualTo("leadId",lead.getLeadId()).get().get().getDocuments();
				for(QueryDocumentSnapshot bs : bidQuery) {
					Bid bid = bs.toObject(Bid.class);
					if(bid.getTransporterId().equals(transporterId)) {
						status = true;
						break;
					}
				}
				if(!status) {
					al.add(lead);
				}
			}
			status = false;
		}
		Collections.sort(al,new Lead());
		return al;
	}
		
	//delete lead by leadId
		public Lead deleteLeadByLeadId(String leadId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
			Firestore dbFirestore = FirestoreClient.getFirestore();
			Lead lead =  dbFirestore.collection(TAG).document(leadId).get().get().toObject(Lead.class);
			dbFirestore.collection(TAG).document(leadId).delete();
		    return lead;
		}
		

}
