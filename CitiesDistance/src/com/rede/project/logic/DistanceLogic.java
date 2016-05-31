package com.rede.project.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rede.project.pojo.CityDistance;
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
		
		double latCityA = cityA.getLatitude();
		double latCityB = cityB.getLatitude();
		double lngCityA = cityA.getLongitude();
		double lngCityB = cityB.getLongitude();
		
		double distance = calculeHaversine(latCityA, latCityB, lngCityA, lngCityB);	
		
		if(unit != null && "MI".equalsIgnoreCase(unit)){
			distance = convertKmToMiles(distance);		
		}
		
		return (double) Math.round(distance * 100) / 100;		
	}

	public List<CityDistance> getAllDistances(List<City> cities, String unit) {
		
		List<CityDistance> listCitiesDistances = new ArrayList<>();
		List<City> auxListCities =  cities;
		
		for(Iterator<City> iterator = cities.iterator(); iterator.hasNext(); ){
			City mainCity = iterator.next();			
			for(City secondaryCity: auxListCities){
				CityDistance distance = new CityDistance();
				if(!mainCity.getId().equals(secondaryCity.getId())){
					distance.setDistance(getDistance(mainCity, secondaryCity, unit));
					distance.setCityA(mainCity);
					distance.setCityB(secondaryCity);
					listCitiesDistances.add(distance);
				}
			}
			iterator.remove();
		}
				
		return listCitiesDistances;
	}
	
}
