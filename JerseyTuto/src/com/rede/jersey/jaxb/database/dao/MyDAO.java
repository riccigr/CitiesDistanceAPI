package com.rede.jersey.jaxb.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rede.jersey.jaxb.database.DatabaseConnection;

public class MyDAO {

	private Connection connection;

	public MyDAO() {
		this.connection = new DatabaseConnection().getConnection();
	}

	public void select() {

		try {
			String sql = "select * from testetable";

			PreparedStatement stm = this.connection.prepareStatement(sql);

			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString("nome"));
			}
			
			rs.close();
			stm.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
