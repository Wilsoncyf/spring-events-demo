package com.example.springeventsdemo.service;

import com.example.springeventsdemo.domain.Order;
import com.example.springeventsdemo.event.OrderCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async; // 需要异步
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    // 监听订单创建事件
    // 使用 @Async 实现异步执行
    // 使用 condition 实现条件：只处理金额大于 100 的订单
//    @EventListener(condition = "#event.order.amount.compareTo(T(java.math.BigDecimal).valueOf(100)) > 0")
    @Async // 异步执行
    @EventListener(condition = "#event.order.amount.compareTo(new java.math.BigDecimal('100.00')) > 0") // <-- 修改这里
    public void handleOrderCreationForNotification(OrderCreatedEvent event) {
        Order order = event.getOrder();
        String threadName = Thread.currentThread().getName(); // 获取线程名

        // 模拟发送通知前的准备或延迟
        try {
            System.out.println();
            System.out.println("===== NotificationService (@EventListener @Async, Condition > 100, 线程: " + threadName + ") =====");
            System.out.println("事件信息: 收到订单创建事件 (ID: " + order.getOrderId() + ", 金额: " + order.getAmount() + ")。条件满足，准备发送通知 (模拟耗时1秒)... 😴");
            Thread.sleep(1000); // 模拟耗时
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("操作: (异步) 正在向用户 " + order.getUserId() + " 发送订单 " + order.getOrderId() + " 的确认通知 (模拟)...");
        // 实际场景: 调用短信、邮件或 App 推送服务
        // notificationClient.sendOrderConfirmation(order.getUserId(), order.getOrderId());
        System.out.println("操作: (异步) 模拟通知发送成功！");
        System.out.println("===============================================================================");
        System.out.println();
    }

    // (可选) 可以添加另一个监听器处理金额小于等于 100 的订单，或者不处理
//    @EventListener(condition = "#event.order.amount.compareTo(T(java.math.BigDecimal).valueOf(100)) <= 0")
    @EventListener(condition = "#event.order.amount.compareTo(new java.math.BigDecimal('100.00')) <= 0") // <-- 修改这里！
    @Async
    public void handleLowAmountOrder(OrderCreatedEvent event) {
         System.out.println();
         System.out.println("===== NotificationService (@EventListener @Async, Condition <= 100) =====");
         System.out.println("事件信息: 收到低金额订单 (ID: " + event.getOrder().getOrderId() + ")，不发送特殊通知。");
         System.out.println("=====================================================================");
         System.out.println();
    }
}