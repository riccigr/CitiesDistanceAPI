package com.rede.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.jfree.util.Log;

import com.rede.project.factory.ConnectionFactory;
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
			Log.error(e);
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
			Log.error(e);
		}

	}
}
