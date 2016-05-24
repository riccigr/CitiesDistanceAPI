package com.rede.project.logic;

import java.util.Map;

import com.rede.project.dao.CityDAO;
import com.rede.project.resource.City;

public class DistanceLogic {

	private double calculeHaversine(double latCityA, double latCityB, double lngCityA, double lngCityB) {

		double earthradius = 6371000;
		double dlat = Math.toRadians(latCityB - latCityA);
		double dLong = Math.toRadians(lngCityB - lngCityA);
		double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(Math.toRadians(latCityA))
				* Math.cos(Math.toRadians(latCityB)) * Math.sin(dLong / 2) * Math.sin(dLong / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return (earthradius * c) / 1000;
		
	}
	
	private double convertKmToMiles(double km){
		return km * 0.621;		
	}
	
	
	public double getDistance(City cityA, City cityB, String unit ){
		
		
		Map<String, Double> mapCityA = new CityDAO().getLatLongById(cityA.getId());
		Map<String, Double> mapCityB = new CityDAO().getLatLongById(cityB.getId());
		
		double latCityA = mapCityA.get("latitude").doubleValue();
		double latCityB = mapCityB.get("latitude").doubleValue();
		double lngCityA = mapCityA.get("longitude").doubleValue();
		double lngCityB = mapCityB.get("longitude").doubleValue();
		
		double distance = calculeHaversine(latCityA, latCityB, lngCityA, lngCityB);	
		
		if(unit != null && "MILES".equalsIgnoreCase(unit)){
			distance = convertKmToMiles(distance);		
		}
		
		return (double) Math.round(distance * 100) / 100;		
	}
	
}
