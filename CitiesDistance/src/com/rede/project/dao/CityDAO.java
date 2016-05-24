package com.rede.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.rede.project.factory.ConnectionFactory;
import com.rede.project.log.LogHelper;
import com.rede.project.resource.City;

public class CityDAO {
	private Connection connection;

	@SuppressWarnings("static-access")
	public CityDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public Map<String, Double> getLatLongById(String id) {

		HashMap<String, Double> result = new HashMap<>();

		try {
			String sql = "select latitude, longitude from city where id = ?";

			PreparedStatement stm = this.connection.prepareStatement(sql);
			stm.setString(1, id);

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				result.put("latitude", rs.getDouble("latitude"));
				result.put("longitude", rs.getDouble("longitude"));
			}

			rs.close();
			stm.close();
			connection.close();

		} catch (Exception e) {
			LogHelper.log.error(e);
			return null;
		}

		return result;

	}
	
	public Map<String, City> getCity(String idCity) {

		HashMap<String, City> result = new HashMap<>();
		String id;
		String name;
		double latitude;
		double longitude;

		try {
			String sql = "select id, name, latitude, longitude from city where id = ?";

			PreparedStatement stm = this.connection.prepareStatement(sql);
			stm.setString(1, idCity);

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				id = rs.getString("id");
				name = rs.getString("name");
				latitude = rs.getDouble("latitude");
				longitude = rs.getDouble("longitude");			
				
				City city = new City(id, name, latitude, longitude);
				result.put(id, city);
				
			}

			rs.close();
			stm.close();
			connection.close();

		} catch (Exception e) {
			LogHelper.log.error(e);
			return null;
		}

		return result;

	}

	public void createCity(City city) {

		try {
			String sql = "insert into city  (id, name, latitude, longitude) values ( ?, ?, ?, ?)";

			PreparedStatement stm = this.connection.prepareStatement(sql);
			stm.setString(1, city.getId());
			stm.setString(2, city.getName());
			stm.setDouble(3, city.getLatitude());
			stm.setDouble(4, city.getLongitude());

			ResultSet rs = stm.executeQuery();

			rs.close();
			stm.close();
			connection.close();

		} catch (Exception e) {
			LogHelper.log.error(e);
		}

	}
}
