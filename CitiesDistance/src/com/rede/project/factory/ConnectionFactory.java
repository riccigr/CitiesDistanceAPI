package com.rede.project.factory;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionFactory {
	public static String status = "fail to connect...";

	//--
	public static Connection getConnection() {
		
		Connection connection = null;

		try {

			String driverName = "com.mysql.jdbc.Driver";  
			Class.forName(driverName);
			
			String serverName = "localhost";
			String mydatabase = "project";
			String port = "3306";
			String url = "jdbc:mysql://" + serverName +":"+ port + "/" + mydatabase;
			String username = "root";
			String password = "admin";

			connection = (Connection) DriverManager.getConnection(url, username, password);

			if (connection != null) {

				status = ("STATUS--->OK!");

			} else {
				
				status = ("STATUS--->Fail");

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
            ConnectionFactory.getConnection().close();
            return true;

        } catch (SQLException e) {
            return false;
        }

    }
}
