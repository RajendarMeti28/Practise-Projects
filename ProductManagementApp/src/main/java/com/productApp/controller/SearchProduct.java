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
@WebServlet("/searchProduct")
public class SearchProduct extends HttpServlet {
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
        
        int productCode = Integer.parseInt(jsonObject.getString("productCode"));
        
        IProductService serviceObj = ProductFactory.getServiceObj();
        
        Product product = serviceObj.searchProduct(productCode);
        System.out.println(product);
        
        // construct json answer.
        // based on https://www.youtube.com/watch?v=BPMVC999HTs
        JsonObject root = null;
        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();
        JsonObjectBuilder userBuilder = Json.createObjectBuilder();
        
        if(product!=null) {
        	userBuilder.add("productCode",product.getProductCode())
        	.add("productName", product.getProductName())
        	.add("productPrice",product.getProductPrice())
        	.add("productRating",product.getProductRating());
        	 root = rootBuilder.add("product", userBuilder).build();
        }else {
        	root = rootBuilder.add("product", "").build();
        }
        // write response to out
        
        System.out.println(root);
        
        PrintWriter out = response.getWriter();
        out.println(root);
        out.flush();
        out.close();
	}

}
