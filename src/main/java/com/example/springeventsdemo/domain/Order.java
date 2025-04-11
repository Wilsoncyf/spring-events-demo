package com.example.springeventsdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal; // 使用 BigDecimal 处理金额更精确

@Getter
@AllArgsConstructor
@ToString
public class Order {
    private String orderId;   // 订单 ID
    private String productId; // 产品 ID
    private int quantity;     // 数量
    private BigDecimal amount;    // 订单金额
    private String userId;      // 用户 ID
}