package com.eagleshipperapi.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eagleshipperapi.FileUtility;
import com.eagleshipperapi.bean.Transporter;
import com.eagleshipperapi.bean.Vehicle;
import com.eagleshipperapi.exception.ResourceNotFoundException;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class VehicleService {

	
	//create new vehicle
	public Vehicle createVehicle(MultipartFile file,Vehicle vehicle,String id) throws IOException, InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		
		String imageUrl = new FileUtility().getImageUrl(file);
		String vehicelId = firestore.collection("Transporter").document().getId();
		vehicle.setVehicelId(vehicelId);
		vehicle.setImageUrl(imageUrl);
		Transporter transporter = firestore.collection("Transporter").document(id).get().get().toObject(Transporter.class);
		ArrayList<Vehicle>vehicleList = transporter.getVehicleList();
		if(vehicleList == null)
			vehicleList = new ArrayList<>();
		vehicleList.add(vehicle);
		transporter.setVehicleList(vehicleList);
		firestore.collection("Transporter").document(id).set(transporter);		
		return vehicle;
	}
	
	//delete vehicle
	public Vehicle deleteVehicle(String vehicleId,String transporterId) throws InterruptedException, ExecutionException, ResourceNotFoundException {	
		Firestore firestore = FirestoreClient.getFirestore();
			
		Transporter t = firestore.collection("Transporter").document(transporterId).get().get().toObject(Transporter.class);
			Vehicle vehicle = null;
			ArrayList<Vehicle> vehicleList = t.getVehicleList();
			for(Vehicle v : vehicleList) {
				if(v.getVehicelId().equals(vehicleId)) {
					vehicle  = v;
					vehicleList.remove(v);
					break;
				}
			}
			t.setVehicleList(vehicleList);
			firestore.collection("Transporter").document(transporterId).set(t);
			return vehicle;
	}
	
	
	//update vehicle
	public Vehicle updateVehicle(String transporterId,Vehicle vehi) throws InterruptedException, ExecutionException, ResourceNotFoundException, IOException {
		Firestore firestore = FirestoreClient.getFirestore();
			
		Transporter t = firestore.collection("Transporter").document(transporterId).get().get().toObject(Transporter.class);
			ArrayList<Vehicle> vehicleList = t.getVehicleList();
			int i =0;
			for(Vehicle v : vehicleList) {
				if(v.getVehicelId().equals(vehi.getVehicelId()))
					vehicleList.set(i,vehi);
				i++;
			}
			firestore.collection("Transporter").document(transporterId).set(t);
			return vehi;
	}
}