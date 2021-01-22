package com.eagleshipperapi.bean;

public class Vehicle {
	private String vehicelId;
	private String name;
	private int count;
	private String imageUrl;

	public Vehicle(String vehicelId, String name, int count, String imageUrl) {
		super();
		this.vehicelId = vehicelId;
		this.name = name;
		this.count = count;
		this.imageUrl = imageUrl;
	}

	public Vehicle() {
	}

	public String getVehicelId() {
		return vehicelId;
	}

	public void setVehicelId(String vehicelId) {
		this.vehicelId = vehicelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
