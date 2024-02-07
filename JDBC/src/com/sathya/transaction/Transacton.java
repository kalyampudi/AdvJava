package com.sathya.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Transacton {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
  Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hasini","hasini");
				Statement statement = connection.createStatement();
				
				connection.setAutoCommit(false);
				try
				{
				statement.executeUpdate("insert into emp values(101,'Hasini',34435)");
				statement.executeUpdate("insert into emp values(102,'Nithin',47538)");
				statement.executeUpdate("insert into emp values(103,'Anshu',783455)");
				statement.executeUpdate("delete from emp where eid=103");
				connection.commit();
				System.out.println("Transaction sucessful....");
				}
				catch (SQLException e) {
					connection.rollback();
					System.out.println("Transaction Fail & Rollbacked");			
				}
				finally {
					connection.close();
					statement.close();
				}
				System.out.println("Operations are completed...");
				
			}

	}
