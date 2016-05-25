package com.rede.project.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jfree.util.Log;

import com.rede.project.util.Utils;


public class ConnectionFactory {
	
	 public ConnectionFactory(){}
	
	//--
	public static Connection getConnection() {
		
		try {

			String driverName = "com.mysql.jdbc.Driver";  
			Class.forName(driverName);
			
			String serverName = "localhost";
			String mydatabase = "project";
			String port = "3306";
			String url = "jdbc:mysql://" + serverName +":"+ port + "/" + mydatabase;
			String username = "root";
			String password = "admin";//Utils.getDatabaseAccess();

			return DriverManager.getConnection(url, username, password);

		
		} catch (Exception e) {
			Log.error(e);
			return null;
		}


	}	
    
    //---
    public static boolean closeConnection() {

        try {
            ConnectionFactory.getConnection().close();
            return true;

        } catch (SQLException e) {
        	Log.error(e);
            return false;
        }

    }
}
