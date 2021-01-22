package com.eagleshipperapi.service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.eagleshipperapi.bean.States;
import com.eagleshipperapi.exception.ResourceNotFoundException;
import com.google.api.core.ApiFuture;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class StateService {
	
	
	public ArrayList<States> getStateList() throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();	
		ArrayList<States> al = new ArrayList<>();
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("State").get();
		QuerySnapshot querySnapshot = apiFuture.get();
		List<QueryDocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documentSnapshotList) {
			States state = document.toObject(States.class);
			al.add(state);
		}
		return al;
	}
    
	public States saveState(States s) throws Exception {
		Firestore fireStore = FirestoreClient.getFirestore();
		fireStore.collection("State").document(s.getStateId()).set(s);
		return s;
	}
	  public States getStateById(String stateId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
		  Firestore fireStore = FirestoreClient.getFirestore();
		  States state = fireStore.collection("State").document(stateId).get().get().toObject(States.class);
	      if (state!= null) 
	    	  return state;
	      else
	    	  throw new ResourceNotFoundException("state not found for this id "+stateId);
	    	  
	  }

}