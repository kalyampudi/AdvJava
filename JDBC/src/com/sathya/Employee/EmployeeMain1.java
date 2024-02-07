package com.sathya.Employee;

import java.util.List;

import com.sathya.Product.Product;
import com.sathya.Product.ProductDao;

import oracle.net.aso.d;
import oracle.net.aso.e;
import oracle.net.aso.p;

public class EmployeeMain1{

	public static void main(String[] args) {
		EmployeeDao dao = new EmployeeDao();
		int createResult = dao.createEmployeetable();
		System.out.println("Table creation status..."+createResult);
	}

}

class EmployeeMain2{
	public static void main(String[] args)
	{
		EmployeeDao dao = new EmployeeDao();
		Employee employee = new Employee();
		employee.setEmpName("Hasini");
		employee.setEmpId("1234");
		employee.setEmpSalary(100000);
		int res = dao.saveEmp(employee);
		if(res == 1)
		{
			System.out.println("Data inserted sucessfully..."+res);
		}
		else
		{
			System.out.println("Data insertion failed....."+res);
		}	
	}	
}
class EmployeeMain3{
	public static void main(String[] args)
	{
		EmployeeDao dao = new EmployeeDao();
		Employee employee = dao.FindById("1234");
		if(employee != null)
		{
			System.out.println("Employee Id is available");
			System.out.println(employee.getEmpName()+" "+employee.getEmpId()+" "+employee.getEmpSalary());
		}
		else
		{
			System.out.println("Employee Id is not available");
		}
	}
}
class EmployeeMain4{
	public static void main(String[] args)
	{
		EmployeeDao dao = new EmployeeDao();
		List<Employee> employee = dao.FindAll();
		employee.forEach(emp->System.out.println(emp));
	}	
}

class EmployeeMain5{
	public static void main(String[] args)
	{
		EmployeeDao dao = new EmployeeDao();
		int count = dao.updateEmployee(200, 100);
		System.out.println("update record count...."+count);
	}
}

class EmployeeMain6{
	public static void main(String[] args)
	{
		EmployeeDao dao = new EmployeeDao();
		int count = dao.deleteEmployeeById("1234");
		System.out.println("delete records count...."+count);
	}
}

class EmployeeMain7{
	public static void main(String[] args)
	{
		EmployeeDao dao = new EmployeeDao();
		int count = dao.deleteEmployeeBySalary(200);
		System.out.println("delete records count..."+count);
	}
}

class EmployeeMain8
{
	public static void main(String[] args)
	{
		EmployeeDao dao = new EmployeeDao();
		int count = dao.dropTable();
		System.out.println("Table droped status is..."+count);
	}
}