package com.sathya.Product;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.net.aso.p;

public class ProductDao {
	
	public int createProducttable()
	{
		Connection connection = null; 
		Statement statement =  null; 
		int result = 0; 
		
		try {
		connection = DataBaseConnectionUtils.createConnection();	
		statement = connection.createStatement();
		

		result = statement.executeUpdate(("create table PRODUCT_INFO(proId varchar2(30) primary key, proName varchar2(20), proPrice number)"));
		}
		catch(SQLException e) {
		    e.printStackTrace();
		    System.out.println("Error creating table: " + e.getMessage());
		}

		finally {
		    try {
		        if (statement != null) 
		            statement.close();
		        
		        if (connection != null) 
		            connection.close();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		return result;
	}
	
	public int saveProduct(Product p) {
		int result =0;
		try(Connection connection = DataBaseConnectionUtils.createConnection()) 
		{
		//create the ps object with params
			PreparedStatement preparedStatement = connection.prepareStatement("insert into PRODUCT_INFO values(?,?,?)");
			
		//set the data to params
			preparedStatement.setString(1, p.getProId());
			preparedStatement.setString(2, p.getProName());
			preparedStatement.setDouble(3, p.getProPrice());
			
		//execute the query
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public Product findById(String ProId)
	{
		Product p  = null;
		try(Connection connection = DataBaseConnectionUtils.createConnection())
		{
			// create the ps object
			PreparedStatement preparedStatement = connection.prepareStatement("select * from PRODUCT_INFO where ProId =?");
			
			// set the data to params
			preparedStatement.setString(1, ProId);
			//execute the query
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				p = new Product();
				p.setProId(resultSet.getString(1));
				p.setProName(resultSet.getString(2));
				p.setProPrice(resultSet.getDouble(3));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return p;	
	}
	
	
	public List<Product> findAll()
	{
		List<Product> products = new ArrayList<Product>();
		Product p = null;
		try(Connection connection = DataBaseConnectionUtils.createConnection()) 
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from PRODUCT_INFO");
			while (resultSet.next())
			{
				//read the data from ResultSet & setting to product
				p = new Product();
				p.setProId(resultSet.getString(1));
				p.setProName(resultSet.getString(2));
				p.setProPrice(resultSet.getDouble(3));
				
				//adding the product into list
				products.add(p);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	

	public int updateProduct(double price, double incValue)
	{
		int result =0;
		try(Connection connection = DataBaseConnectionUtils.createConnection())
		{
			//create the PS object 
			PreparedStatement preparedStatement = connection.prepareStatement("update PRODUCT_INFO set ProPrice = ProPrice+? where ProPrice>?");
			
			//set the data to params
			preparedStatement.setDouble(1, incValue);
			preparedStatement.setDouble(2, price);
			
			//execute the query
			
			result = preparedStatement.executeUpdate();	
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteProductById(String proId)
	{
		int result =0;
		try(Connection connection = DataBaseConnectionUtils.createConnection())
		{
			//create the PS object 
			PreparedStatement preparedStatement= connection.prepareStatement("delete from PRODUCT_INFO where proId=? ");
			//set the data to params
			preparedStatement.setString(1, proId);
			//execute the query
			result = preparedStatement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteProductByPrice(double proPrice)
	{
		int result =0;
		try(Connection connection= DataBaseConnectionUtils.createConnection())
		{
			//create the PS object
			PreparedStatement preparedStatement = connection.prepareStatement("delete from PRODUCT_INFO where proPrice>=? ");
			//set the data to params
			preparedStatement.setDouble(1, 200);
			//execute the query
			result = preparedStatement.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	public int droptable()
	{
		int result=0;
	try (Connection connection = DataBaseConnectionUtils.createConnection())
			{
		//create the statement object
		Statement statement = connection.createStatement();
		//execute the query
		result = statement.executeUpdate("drop table PRODUCT_INFO ");
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
		return result;
		
	}
}




