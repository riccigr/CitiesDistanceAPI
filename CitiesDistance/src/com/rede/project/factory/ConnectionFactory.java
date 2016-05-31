package com.rede.project.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.rede.project.log.LogHelper;


public class ConnectionFactory {
	
	private static ConnectionFactory dbInstance;
	
	private ConnectionFactory(){}
	
	public static ConnectionFactory getInstance(){
		if(dbInstance == null){
			dbInstance = new ConnectionFactory();
		}
		return dbInstance;
	}

	
	public Connection getConnection() {
		
		try {

			String driverName = "com.mysql.jdbc.Driver";  
			Class.forName(driverName);
			
			String serverName = "localhost";
			String mydatabase = "project";
			String port = "3306";
			String url = "jdbc:mysql://" + serverName +":"+ port + "/" + mydatabase;
			String username = "root";
			String password = "admin";

			return DriverManager.getConnection(url, username, password);

		
		} catch (Exception e) {
			LogHelper.LOG.error(e);
			return null;
		}


	}	
    
    public static boolean closeConnection() {

        try {
            ConnectionFactory.getInstance().getConnection().close();
            return true;

        } catch (SQLException e) {
        	LogHelper.LOG.error(e);
            return false;
        }

    }
}
