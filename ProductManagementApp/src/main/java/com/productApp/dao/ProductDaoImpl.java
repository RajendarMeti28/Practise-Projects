package com.productApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.productApp.dto.Product;
import com.productApp.util.JdbcUtil;

public class ProductDaoImpl implements IProductDao {

	@Override
	public String addProduct(Product product) {
		
		String insertQuery = "insert into product(`productCode`,`productName`,`productPrice`,`productRating`) values(?,?,?,?)";
		
		try {
			Connection connection = JdbcUtil.getConnection();
			PreparedStatement stmt = connection.prepareStatement(insertQuery);
			
			stmt.setInt(1, product.getProductCode());
			stmt.setString(2, product.getProductName());
			stmt.setInt(3, product.getProductPrice());
			stmt.setFloat(4, product.getProductRating());
			
			int noOfRows = stmt.executeUpdate();
			
			if(noOfRows == 1) {
				return "success";
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failed";
	}

	@Override
	public Product searchProduct(int productCode) {
		String selectQuery = "select productCode,productName,productPrice,productRating from product where productCode = ?";
		Product product = null;
		try {
			Connection connection = JdbcUtil.getConnection();
			PreparedStatement stmt = connection.prepareStatement(selectQuery);
			stmt.setInt(1, productCode);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				product = new Product();
				product.setProductCode(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductPrice(rs.getInt(3));
				product.setProductRating(rs.getFloat(4));
			}
	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public String updateProduct(Product product) {
		String updateQuery = "update  product set productName=?, productPrice=?, productRating=? where productCode = ?";
		
		try {
			Connection connection = JdbcUtil.getConnection();
			PreparedStatement stmt = connection.prepareStatement(updateQuery);
			
			
			stmt.setString(1, product.getProductName());
			stmt.setInt(2, product.getProductPrice());
			stmt.setFloat(3, product.getProductRating());
			stmt.setInt(4, product.getProductCode());
			
			int noOfRows = stmt.executeUpdate();
			
			if(noOfRows == 1) {
				return "success";
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failed";
	}

	@Override
	public String deleteProduct(int productCode) {
		String deleteQuery = "delete from product where productCode = ?";
		
		try {
			Connection connection = JdbcUtil.getConnection();
			PreparedStatement stmt = connection.prepareStatement(deleteQuery);
			
			stmt.setInt(1, productCode);
			
			int noOfRows = stmt.executeUpdate();
			
			if(noOfRows == 1) {
				return "success";
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failed";
	}

}
