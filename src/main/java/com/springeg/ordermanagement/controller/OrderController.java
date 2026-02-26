package com.springeg.ordermanagement.controller;

import com.springeg.ordermanagement.domain.entity.Order;
import com.springeg.ordermanagement.dto.OrderRequest;
import com.springeg.ordermanagement.dto.OrderResponse;
import com.springeg.ordermanagement.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderResponse> placeOrder(
          @Valid @RequestBody OrderRequest request ){
    Order order = orderService.placeOrder(
            request.getProductId(),
            request.getQuantity()
    );

    OrderResponse response = mapToResponse(order);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  private OrderResponse mapToResponse(Order order){
    return OrderResponse.builder()
            .id(order.getId())
            .productId(order.getProductId())
            .quality(order.getQuantity())
            .totalAmount(order.getTotalAmount())
            .status(order.getStatus())
            .createdAt(order.getCreatedAt())
            .build();
  }
}
