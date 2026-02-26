package com.springeg.ordermanagement.service;

import com.springeg.ordermanagement.domain.entity.Product;
import com.springeg.ordermanagement.dto.ProductRequest;

import java.util.List;

public interface ProductService {
  Product createProduct(ProductRequest request);
  List<Product> getAllProducts();
  Product getProductById(Long id);
  Product updateProduct(Long id, ProductRequest request);
  Product activateProduct(Long id, Boolean active);
}

