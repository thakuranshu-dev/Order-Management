package com.springeg.ordermanagement.service.impl;

import com.springeg.ordermanagement.domain.entity.Order;
import com.springeg.ordermanagement.domain.entity.Product;
import com.springeg.ordermanagement.domain.enums.OrderStatus;
import com.springeg.ordermanagement.exception.BusinessException;
import com.springeg.ordermanagement.exception.InsufficientStockException;
import com.springeg.ordermanagement.exception.ResourceNotFoundException;
import com.springeg.ordermanagement.repository.OrderRepository;
import com.springeg.ordermanagement.repository.ProductRepository;
import com.springeg.ordermanagement.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
  private final ProductRepository productRepository;
  private final OrderRepository orderRepository;

  @Override
  public Order placeOrder(Long productId, Integer quantity){
    Product product = productRepository.findById(productId)
            .orElseThrow(()-> new ResourceNotFoundException("Product not found"));

    if (!product.getIsActive()){
      throw new BusinessException("Product is inactive");
    }

    if (product.getStockQuantity() < quantity){
      throw new InsufficientStockException("Not enough stock");
    }

    BigDecimal total = product.getPrice()
            .multiply(BigDecimal.valueOf(quantity));

    product.setStockQuantity(product.getStockQuantity() - quantity);

    Order order = Order.builder()
            .productId(productId)
            .quantity(quantity)
            .totalAmount(total)
            .status(OrderStatus.CREATED)
            .build();

    return orderRepository.save(order);
  }

  @Override
  @Transactional
  public Order cancelOrder(Long orderId){
    Order order = orderRepository.findById(orderId)
            .orElseThrow(()-> new ResourceNotFoundException("Order not found with id: " + orderId));

    if (order.getStatus() != OrderStatus.CREATED){
      throw new BusinessException("Only orders with status CREATED can be canceled.");
    }

    Product product = productRepository.findById(order.getProductId())
            .orElseThrow(()-> new ResourceNotFoundException("Product not found with id: " + order.getProductId()));

    product.setStockQuantity((product.getStockQuantity() + order.getQuantity()));
    productRepository.save(product);

    order.setStatus(OrderStatus.CANCELLED);

    return orderRepository.save(order);
  }
}
