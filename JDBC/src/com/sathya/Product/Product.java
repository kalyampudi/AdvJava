package com.sathya.Product;

public class Product {
private static String ProId;
private String ProName;
private Double ProPrice;
public static String getProId() {
	return ProId;
}
public void setProId(String proId) {
	ProId = proId;
}
public String getProName() {
	return ProName;
}
public void setProName(String proName) {
	ProName = proName;
}
public Double getProPrice() {
	return ProPrice;
}
public void setProPrice(double proPrice) {
	ProPrice = proPrice;
}
@Override
public String toString() {
	return "Product [ProId=" + ProId + ", ProName=" + ProName + ", ProPrice=" + ProPrice + "]";
}

}
