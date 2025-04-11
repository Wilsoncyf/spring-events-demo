package com.example.springeventsdemo.service;

import com.example.springeventsdemo.domain.Order;
import com.example.springeventsdemo.event.OrderCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class InventoryService {

    // 监听订单创建事件，且只在事务成功提交后执行
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderCreation(OrderCreatedEvent event) {
        Order order = event.getOrder();
        System.out.println();
        System.out.println("===== InventoryService (@TransactionalEventListener - AFTER_COMMIT) =====");
        System.out.println("事件信息: 订单 " + order.getOrderId() + " 创建事务已提交。");
        System.out.println("操作: 正在为产品 " + order.getProductId() + " 扣减库存 " + order.getQuantity() + " 个 (模拟)...");
        // 实际场景: 调用库存管理逻辑
        // stockManager.decreaseStock(order.getProductId(), order.getQuantity());
        System.out.println("操作: 模拟库存扣减成功！");
        System.out.println("=====================================================================");
        System.out.println();
    }
}