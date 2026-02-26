package com.springeg.ordermanagement.service.impl;

import com.springeg.ordermanagement.domain.entity.Product;
import com.springeg.ordermanagement.dto.ProductRequest;
import com.springeg.ordermanagement.exception.ResourceNotFoundException;
import com.springeg.ordermanagement.repository.ProductRepository;
import com.springeg.ordermanagement.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  @Override
  public Product createProduct(ProductRequest request) {
    Product product = Product.builder()
            .name(request.getName())
            .price(request.getPrice())
            .stockQuantity(request.getStockQuantity())
            .isActive(request.getIsActive())
            .build();

    return productRepository.save(product);
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product getProductById(Long id) {
    return productRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Product not found "));
  }

  @Override
  public Product updateProduct(Long id, ProductRequest request) {
    Product product = getProductById(id);
    product.setName(request.getName());
    product.setPrice(request.getPrice());
    product.setStockQuantity(request.getStockQuantity());
    product.setIsActive(request.getIsActive());

    return productRepository.save(product);
  }

  @Override
  public Product activateProduct(Long id, Boolean active) {
    Product product = getProductById(id);
    product.setIsActive(active);

    return productRepository.save(product);
  }

}
