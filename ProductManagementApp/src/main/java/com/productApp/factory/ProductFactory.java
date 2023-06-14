package com.productApp.factory;

import com.productApp.dao.IProductDao;
import com.productApp.dao.ProductDaoImpl;
import com.productApp.services.IProductService;
import com.productApp.services.ProductServiceImpl;

public class ProductFactory {
	
	private ProductFactory() {
		
	}
	
	private static IProductService productServiceObj = null;
	private static IProductDao productDaoObj = null;
	
	public static IProductService getServiceObj() {
		if(productServiceObj==null) {
			productServiceObj = new ProductServiceImpl();
		}
		return productServiceObj;
	}
	
	public static IProductDao getDaoObj() {
		if(productDaoObj == null) {
			productDaoObj = new ProductDaoImpl();
		}
		return productDaoObj;
	}
	

}
