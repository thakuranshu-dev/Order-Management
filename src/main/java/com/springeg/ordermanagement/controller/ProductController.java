package com.springeg.ordermanagement.controller;

import com.springeg.ordermanagement.domain.entity.Product;
import com.springeg.ordermanagement.dto.ProductRequest;
import com.springeg.ordermanagement.dto.ProductResponse;
import com.springeg.ordermanagement.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ProductResponse> createProduct(
          @Valid @RequestBody ProductRequest request ){
    Product product = productService.createProduct(request);
    ProductResponse response = mapToResponse(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAllProducts(){
    List<Product> products = productService.getAllProducts();
    List<ProductResponse> responses = products.stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    return ResponseEntity.ok(responses);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> getProductById(
          @PathVariable Long id ){
    Product product = productService.getProductById(id);
    return ResponseEntity.ok(mapToResponse(product));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponse> updateProduct(
          @PathVariable Long id,
          @Valid @RequestBody ProductRequest request){
    Product updated = productService.updateProduct(id, request);
  return ResponseEntity.ok(mapToResponse(updated));
  }

  @PatchMapping("/{id}/activate")
  public ResponseEntity<ProductResponse> activateProduct(
          @PathVariable Long id,
          @RequestParam Boolean active
  ){
    Product updated = productService.activateProduct(id, active);
    return ResponseEntity.ok(mapToResponse(updated));
  }

  private ProductResponse mapToResponse(Product product){
    return ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice())
            .stockQuantity(product.getStockQuantity())
            .isActive(product.getIsActive())
            .build();
  }
}
