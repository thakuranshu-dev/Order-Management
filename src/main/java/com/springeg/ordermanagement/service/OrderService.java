package com.springeg.ordermanagement.service;

import com.springeg.ordermanagement.domain.entity.Order;

public interface OrderService {
  Order placeOrder(Long productId, Integer quantity);
  Order cancelOrder(Long OrderId);
}
