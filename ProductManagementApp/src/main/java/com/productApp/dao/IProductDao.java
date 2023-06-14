package com.productApp.dao;

import com.productApp.dto.Product;

public interface IProductDao {
	String addProduct(Product product);
	Product searchProduct(int productCode);
	String updateProduct(Product product);
	String deleteProduct(int productCode);
}
