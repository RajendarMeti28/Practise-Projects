package com.productApp.services;

import com.productApp.dto.Product;

public interface IProductService {
	
	String addProduct(Product product);
	Product searchProduct(int productCode);
	String updateProduct(Product product);
	String deleteProduct(int productCode);
}
