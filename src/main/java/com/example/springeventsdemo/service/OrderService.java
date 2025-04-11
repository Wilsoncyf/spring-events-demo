package com.example.springeventsdemo.service;

import com.example.springeventsdemo.domain.Order;
import com.example.springeventsdemo.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 需要事务

import java.math.BigDecimal;
import java.util.UUID; // 用于生成随机订单 ID

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ApplicationEventPublisher eventPublisher;
    // private final OrderRepository orderRepository; // 假设有用于持久化的 Repository

    @Transactional // 标记为事务方法
    public Order createOrder(String productId, int quantity, BigDecimal amount, String userId) {
        String orderId = UUID.randomUUID().toString().substring(0, 8); // 生成简单订单 ID
        System.out.println("\n===== OrderService (Tx) 开始 =====");
        System.out.println("操作: 正在创建订单 (ID: " + orderId + ", Product: " + productId + ", Amount: " + amount + ")");

        Order newOrder = new Order(orderId, productId, quantity, amount, userId);

        // 模拟保存订单到数据库
        // orderRepository.save(newOrder);
        System.out.println("操作: 订单信息已模拟保存到数据库。");

        // 创建并发布订单创建事件
        OrderCreatedEvent event = new OrderCreatedEvent(this, newOrder);
        System.out.println("操作: 正在发布 OrderCreatedEvent (在事务中)...");
        this.eventPublisher.publishEvent(event); // 在事务中发布
        System.out.println("操作: OrderCreatedEvent 已发布。");

        // 模拟其他事务性操作（如果需要）

        System.out.println("===== OrderService (Tx) 事务准备提交 =====");
        return newOrder; // 返回创建的订单
    }
}