package com.example.springeventsdemo.event;

import com.example.springeventsdemo.domain.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderCreatedEvent extends ApplicationEvent {

    private final Order order; // 携带订单信息

    /**
     * 创建订单创建事件实例.
     * @param source 发布事件的源 (通常是 OrderService)
     * @param order  创建的订单对象
     */
    public OrderCreatedEvent(Object source, Order order) {
        super(source);
        System.out.println("调试信息: OrderCreatedEvent 已创建。 Source: "
                           + source.getClass().getSimpleName()
                           + ", OrderId: " + order.getOrderId()
                           + ", Timestamp: " + this.getTimestamp());
        this.order = order;
    }

    // Lombok @Getter 会生成 getOrder()
}