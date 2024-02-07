package com.sathya.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SampleJdbc {
	public static void main(String[]args) {
	Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hasini","hasini");
			System.out.println("Connection created....");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		try
		{
			connection.close();
			System.out.println("connection closed...");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
