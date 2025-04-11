package com.example.springeventsdemo.listener;

import com.example.springeventsdemo.domain.User;
import com.example.springeventsdemo.event.UserRegisteredEvent;
import org.springframework.context.event.EventListener; // 导入注解
import org.springframework.stereotype.Component;

/**
 * 一个监听 UserRegisteredEvent 的监听器示例，使用 @EventListener 注解。
 * 负责为新注册用户增加积分。
 */
@Component // <-- 重要！同样需要是 Spring Bean
public class PointsAdderListener {

    // 使用 @EventListener 注解将此方法标记为事件监听器
    // 方法参数类型 UserRegisteredEvent 决定了它监听哪个事件
    @EventListener
    public void handleUserRegistrationForPoints(UserRegisteredEvent event) { // 方法名可以任意取
        System.out.println(); // 加个空行
        System.out.println("===== PointsAdderListener 开始处理 ====="); // 标识是这个监听器在工作

        // 1. 从事件中获取数据
        User user = event.getUser();
        System.out.println("事件信息: 用户 " + user.getName() + " 已注册。");
        System.out.println("事件来源: " + event.getSource().getClass().getSimpleName());

        // 2. 执行增加积分的逻辑（模拟）
        System.out.println("操作: 正在为用户 " + user.getName() + " 增加注册积分 (模拟 +100 分)...");
        // 实际场景: pointsService.addPoints(user.getId(), 100);
        System.out.println("操作: 模拟增加积分成功！");
        System.out.println("===== PointsAdderListener 处理完毕 =====");
        System.out.println();
    }

    // 注意：你可以在同一个类中定义更多 @EventListener 方法
    // 来监听其他不同类型的事件，例如：
    // @EventListener
    // public void handleOrderPaidEvent(OrderPaidEvent event) {
    //     System.out.println("处理订单支付事件...");
    // }
}