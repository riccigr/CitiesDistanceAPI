package com.rede.project.pojo;

import com.rede.project.resource.City;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CityDistance {

	private City cityA;
	private City cityB;
	private double distance;
	
	public City getCityA() {
		return cityA;
	}
	public void setCityA(City cityA) {
		this.cityA = cityA;
	}
	public City getCityB() {
		return cityB;
	}
	public void setCityB(City cityB) {
		this.cityB = cityB;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
}
