package com.rede.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.rede.project.factory.ConnectionFactory;


public class CityDAO {
	private Connection connection;

	public CityDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public HashMap<String, Double> getLatLong(String name) {
		
		HashMap<String, Double> result = new HashMap<>();

		try {
			String sql = "select latitude, longitude from city where name = ?";

			PreparedStatement stm = this.connection.prepareStatement(sql);
			stm.setString(1, name);

			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				result.put("latitude", rs.getDouble("latitude"));
				result.put("longitude", rs.getDouble("longitude"));				
			}
			
			rs.close();
			stm.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return result;

	}
}
