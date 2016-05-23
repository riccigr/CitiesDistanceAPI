package com.rede.project.logic;

import com.rede.project.resource.City;

public class DistanceLogic {

	private double calculeHaversine(double latCityA, double latCityB, double lngCityA, double lngCityB) {

		double earthradius = 6371000;
		double dlat = Math.toRadians(latCityB - latCityA);
		double dLong = Math.toRadians(lngCityB - lngCityA);
		double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(Math.toRadians(latCityA))
				* Math.cos(Math.toRadians(latCityB)) * Math.sin(dLong / 2) * Math.sin(dLong / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = (earthradius * c) / 1000;
		
		return dist;
	}
	
	private double convertKmToMiles(double km){
		double miles = km * 0.621;
		return miles;
	}
	
	
	public double getDistance(City cityA, City cityB, String unit ){
		double distance = 0;
		
		double latCityA, latCityB, lngCityA, lngCityB;
		
		latCityA = cityA.getLatitude();
		latCityB = cityB.getLatitude();
		lngCityA = cityA.getLongitude();
		lngCityB = cityB.getLongitude();
		
		distance = calculeHaversine(latCityA, latCityB, lngCityA, lngCityB);	
		
		if(unit != null && unit.toUpperCase().equals("MILES")){
			distance = convertKmToMiles(distance);		
		}
		
		distance = (double) Math.round(distance * 100) / 100;
		
		return distance;
	}
	
}
