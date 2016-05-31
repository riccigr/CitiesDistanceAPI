package com.rede.project.provider;

import java.util.HashMap;
import java.util.Map;

import com.rede.project.dao.CityDAO;
import com.rede.project.resource.City;

public enum CityProviderEnum {
	INSTANCE;

	private Map<String, City> contentProvider = new HashMap<>();

	private CityProviderEnum() {
	
		contentProvider = new CityDAO().getCities();

	}
	
	public Map<String, City> getModel(){
		return contentProvider;
	} 
}
