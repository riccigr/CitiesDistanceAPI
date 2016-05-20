package com.rede.jersey.jaxb.database;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DatabaseConnection {
	
	public static String status = "Não conectou...";

	//--
	public static Connection getConnection() {
		
		Connection connection = null;

		try {

			String driverName = "com.mysql.jdbc.Driver";  
			Class.forName(driverName);
			
			String serverName = "localhost";
			String mydatabase = "testedb";
			String url = "jdbc:mysql://" + serverName +":3306"+ "/" + mydatabase;
			String username = "root";
			String password = "admin";

			connection = (Connection) DriverManager.getConnection(url, username, password);

			if (connection != null) {

				status = ("STATUS--->Conectado com sucesso!");

			} else {
				
				status = ("STATUS--->Não foi possivel realizar conexão");

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return connection;

	}
	
	//---
    public static String statusConection() {

        return status;
    }
    
    //---
    public static boolean closeConnection() {

        try {
            DatabaseConnection.getConnection().close();
            return true;

        } catch (SQLException e) {
            return false;
        }

    }

}
