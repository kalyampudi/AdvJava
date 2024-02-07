package com.sathya.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.StatementEvent;

import com.sathya.Product.DataBaseConnectionUtils;
import com.sathya.Product.Product;

import oracle.net.aso.e;
import oracle.net.aso.p;
import oracle.net.aso.r;

public class EmployeeDao {
	public int createEmployeetable()
	{
		Connection connection = null;
		Statement statement = null;
		int result = 0;
		try {
			connection = DataBaseConnectionUtils.createConnection();
			statement = connection.createStatement();	
			result = statement.executeUpdate("create table Employee_Info (empName varchar2(10) primary key , empId varchar2(10), empSalary number)");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error creating table:"+e.getMessage());
		}
		finally {
			try {
				if(statement != null)
					statement.close();
		        if(connection != null)
		        	connection.close();  	
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return result;
		
	}
	
	public int saveEmp(Employee e)
	{
		int result = 0;
		try(Connection connection = DataBaseConnectionUtils.createConnection()) 
		{
			//create PS object with params
			PreparedStatement preparedStatement = connection.prepareStatement("insert into Employee_Info values(?,?,?)");
			
			//set the data to params
			preparedStatement.setString(1, e.getEmpName());
			preparedStatement.setString(2, e.getEmpId());
			preparedStatement.setDouble(3, e.getEmpSalary());
			
		//execute the query
			result = preparedStatement.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return result;
	}
	
	public Employee FindById(String empId)
	{
		Employee employee = null;
		
		try(Connection connection = DataBaseConnectionUtils.createConnection())
		{
			//create the PS object
			PreparedStatement preparedStatement = connection.prepareStatement("Select * from Employee_Info where empId=?");
			
			//set the PS params
			preparedStatement.setString(1, empId);
			
			// execte the query 
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				employee = new Employee();
				employee.setEmpName(resultSet.getString(1));
				employee.setEmpId(resultSet.getString(2));
				employee.setEmpSalary(resultSet.getDouble(3));
						
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
		
	}
	
public List<Employee> FindAll()
{
	List<Employee> employee = new ArrayList<Employee>();
	Employee E = null;
	try(Connection connection =DataBaseConnectionUtils.createConnection())
	{
		Statement statement =connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from Employee_Info");
		
		while(resultSet.next())
		{
			//read the data from Setters & getters
			E = new Employee(); 
			E.setEmpName(resultSet.getString(1));
			E.setEmpId(resultSet.getString(2));
			E.setEmpSalary(resultSet.getDouble(3));
			//add the product to list
			employee.add(E);
		}	
	} 
	catch (Exception e) {
		e.printStackTrace();
	}
	
	return employee;	
}

public int updateEmployee(double salary , double incValue )
{
	int result =0;
	try(Connection connection = DataBaseConnectionUtils.createConnection())
	{
		//create the pS object
		PreparedStatement preparedStatement = connection.prepareStatement("update Employee_Info set empSalary = empSalary+? where empSalary>?");
		
		//set the data to parms
		preparedStatement.setDouble(1, incValue);
		preparedStatement.setDouble(2, salary);
		
		//execute the query
		result = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return result;	
}

public int deleteEmployeeById(String empId)
{
	int result =0;
	try(Connection connection = DataBaseConnectionUtils.createConnection())
	{
		//create the PS object 
		PreparedStatement preparedStatement= connection.prepareStatement("delete from Employee_Info where empId=? ");
		//set the data to params
		preparedStatement.setString(1, empId);
		//execute the query
		result = preparedStatement.executeUpdate();
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
}

public int deleteEmployeeBySalary(double empSalary)
{
	int result = 0;
	try(Connection connection = DataBaseConnectionUtils.createConnection())
	{
		//create the PS object
		PreparedStatement preparedStatement = connection.prepareStatement("delete from Employee_Info where empSalary>=?");
		
		//set the data to params
		preparedStatement.setDouble(1, empSalary);
		
		//execute the query
		result = preparedStatement.executeUpdate();
	} 
	catch (Exception e)
	{
		e.printStackTrace();
	}
	return result;
}

public int dropTable() {
	int result =0;
	try(Connection connection = DataBaseConnectionUtils.createConnection())
	{
		//create the statement object
		Statement statement = connection.createStatement();
		//execute the query
		result = statement.executeUpdate("drop table Employee_Info");
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	return result;
}
}
