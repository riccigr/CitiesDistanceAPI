package com.rede.project.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class City {
	private String id;
	private String name;
	private double latitude;
	private double longitude;
	
	public City(){}
	
	public City(String id, String name, double latitude, double longitude){
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = latitude;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	
}
