package com.sathya.Product;

import java.util.List;

import oracle.net.aso.p;

class ProductMain1 {
	public static void main(String[] args) {
		ProductDao dao = new ProductDao();
		int createResult = dao.createProducttable();
		System.out.println("Table creation status..."+createResult);
	}
}


class ProductMain2 {
	public static void main(String[] args)
	{
		ProductDao dao = new ProductDao();
		Product product = new Product();
		product.setProId("AD124");
		product.setProName("Laptop");
		product.setProPrice(503756);
		int res = dao.saveProduct(product);
		if(res==1)
		{
			System.out.println("data inserted sucessfully..."+res);
		}
		else {
			System.out.println("data insersion failed check once..."+res);
		}	
	}	
}

class ProductMain3 {
	public static void main(String[] args)
	{
		ProductDao dao = new ProductDao();
		Product product = dao.findById("AD124");
		if(product !=null)
		{
			System.out.println("Product Avaliable.....");
			System.out.println(product.getProId()+" "+product.getProName()+" "+product.getProPrice());
		}
		else
		{
			System.out.println("Product is not available");
		}	
	}
}

class ProductMain4 {
	public static void main(String[] args)
	{ 
		ProductDao dao = new ProductDao();
		List<Product> products = dao.findAll();
		products.forEach(product->System.out.println(product));
	}
}

class ProductMain5{
	public static void main(String[] args)
	{
		ProductDao dao = new ProductDao();
		int count = dao.updateProduct(200, 5);
		System.out.println("update records count..."+count);
	}
}

class ProductMain6{
	public static void main(String[] args)
	{
		ProductDao dao = new ProductDao();
		int count = dao.deleteProductById("AD124");
		System.out.println("delete records count...."+count);
	}
}

class ProductMain7
{
	public static void main(String[] args)
	{
		ProductDao dao = new ProductDao();
		int count = dao.deleteProductByPrice(200);
		System.out.println("delete records count...."+count);
	}
}
class ProductMain8
{
	public static void main(String[] args)
	{
		ProductDao dao = new ProductDao();
		int count = dao.droptable();
		System.out.println("Table is dropped status..."+count);
	}
}