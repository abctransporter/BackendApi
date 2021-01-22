package com.eagleshipperapi.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import com.google.firebase.cloud.FirestoreClient;

public class Lead implements Comparator<Lead>{
	private String userId;
	private String leadId;
	private String typeOfMaterial;
	private String weight;
	private String pickUpAddress;
	private String deliveryAddress;
	private String contactForPickup;
	private String contactForDelivery;
	private String dateOfCompletion;
	private long timestamp;
	private String status;
	private String materialStatus;
	private String dealLockedWith;
	private int bidCount;
	private String transporterName;
	private String userName;
	private double amount;
	private String remark;

	public Lead() {

	}

	public Lead(String userId, String leadId, String typeOfMaterial, String weight, String pickUpAddress,
			String deliveryAddress, String contactForPickup, String contactForDelivery, String dateOfCompletion,
			long timestamp, String status, String materialStatus, String dealLockedWith, int bidCount, String transporterName,String userName,double amount,String remark) {
		super();
		this.amount = amount;
		this.remark = remark;
		this.userId = userId;
		this.leadId = leadId;
		this.typeOfMaterial = typeOfMaterial;
		this.weight = weight;
		this.pickUpAddress = pickUpAddress;
		this.deliveryAddress = deliveryAddress;
		this.contactForPickup = contactForPickup;
		this.contactForDelivery = contactForDelivery;
		this.dateOfCompletion = dateOfCompletion;
		this.timestamp = timestamp;
		this.status = status;
		this.materialStatus = materialStatus;
		this.dealLockedWith = dealLockedWith;
		this.bidCount = bidCount;
		this.transporterName =transporterName;
		this.userName = userName;
	}
;
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setTransporterName(String transporterName) {
		this.transporterName = transporterName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getTypeOfMaterial() {
		return typeOfMaterial;
	}

	public void setTypeOfMaterial(String typeOfMaterial) {
		this.typeOfMaterial = typeOfMaterial;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPickUpAddress() {
		return pickUpAddress;
	}

	public void setPickUpAddress(String pickUpAddress) {
		this.pickUpAddress = pickUpAddress;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getContactForPickup() {
		return contactForPickup;
	}

	public void setContactForPickup(String contactForPickup) {
		this.contactForPickup = contactForPickup;
	}

	public String getContactForDelivery() {
		return contactForDelivery;
	}

	public void setContactForDelivery(String contactForDelivery) {
		this.contactForDelivery = contactForDelivery;
	}

	public String getDateOfCompletion() {
		return dateOfCompletion;
	}

	public void setDateOfCompletion(String dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMaterialStatus() {
		return materialStatus;
	}

	public void setMaterialStatus(String materialStatus) {
		this.materialStatus = materialStatus;
	}

	public String getDealLockedWith() {
		return dealLockedWith;
	}

	public void setDealLockedWith(String dealLockedWith) {
		this.dealLockedWith = dealLockedWith;
	}

	public int getBidCount() {
		return bidCount;
	}

	public void setBidCount(int bidCount) {
		this.bidCount = bidCount;
	}
	public String getTransporterName() {
		return transporterName;
	}
	public void setTransporterId(String transporterName) {
		this.transporterName = transporterName;
	}

	@Override
	public int compare(Lead o1, Lead o2) {
		int result = (int) (o2.getTimestamp()-o1.getTimestamp());
		return result;
	}
}
