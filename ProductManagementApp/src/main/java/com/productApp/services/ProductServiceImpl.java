package com.productApp.services;

import com.productApp.dao.IProductDao;
import com.productApp.dto.Product;
import com.productApp.factory.ProductFactory;

public class ProductServiceImpl implements IProductService {

	@Override
	public String addProduct(Product product) {
		IProductDao productDaoObj = ProductFactory.getDaoObj();
		return productDaoObj.addProduct(product);
	}

	@Override
	public Product searchProduct(int productCode) {
		IProductDao productDaoObj = ProductFactory.getDaoObj();
		return productDaoObj.searchProduct(productCode);
	
	}

	@Override
	public String updateProduct(Product product) {
		IProductDao productDaoObj = ProductFactory.getDaoObj();
		return productDaoObj.updateProduct(product);
	}

	@Override
	public String deleteProduct(int productCode) {
		IProductDao productDaoObj = ProductFactory.getDaoObj();
		return productDaoObj.deleteProduct(productCode);
	}

}
