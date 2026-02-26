package com.springeg.ordermanagement.dto;

import com.springeg.ordermanagement.domain.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {
  private Long id;
  private Long productId;
  private Integer quality;
  private BigDecimal totalAmount;
  private OrderStatus status;
  private LocalDateTime createdAt;
}
