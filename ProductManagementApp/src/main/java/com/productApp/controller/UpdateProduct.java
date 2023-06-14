package com.productApp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productApp.dto.Product;
import com.productApp.factory.ProductFactory;
import com.productApp.services.IProductService;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/updateProduct")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        
        String json = sb.toString();

        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
       
        int callNo = Integer.parseInt(jsonObject.getString("call"));
       
        JsonObject root = null;
        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();
        JsonObjectBuilder userBuilder = Json.createObjectBuilder();
        IProductService serviceObj = ProductFactory.getServiceObj();
        if(callNo == 1) {
        	
        	int productCode = Integer.parseInt(jsonObject.getString("productCode"));
             
             Product product = serviceObj.searchProduct(productCode);
             System.out.println(product);  
             
             if(product!=null) {
            	 userBuilder.add("productCode",product.getProductCode())
             	.add("productName", product.getProductName())
             	.add("productPrice",product.getProductPrice())
             	.add("productRating",product.getProductRating());
             	 root = rootBuilder.add("product", userBuilder).build();
             }else {
                 root = rootBuilder.add("product", "").build();
             }

        }else if(callNo == 2){
        	
        	Product product = new Product();
        	
        	 int productCodeUpdate = Integer.parseInt(jsonObject.getString("productCode"));
        	 product.setProductCode(productCodeUpdate);
             String productName = jsonObject.getString("productName");
             product.setProductName(productName);
             int productPrice = Integer.parseInt(jsonObject.getString("productPrice"));
             product.setProductPrice(productPrice);
             float productRating = Float.parseFloat(jsonObject.getString("productRating"));
             product.setProductRating(productRating);
        	
        	
        	String updateResult = serviceObj.updateProduct(product);
        	
        	if(updateResult.equalsIgnoreCase("success"))
        		root = rootBuilder.add("result", "success").build();
        	else
        		root = rootBuilder.add("result", "fail").build();
        }
        
       
        System.out.println(root);
        
        PrintWriter out = response.getWriter();
        out.println(root);
        out.flush();
        out.close();
	}

}
