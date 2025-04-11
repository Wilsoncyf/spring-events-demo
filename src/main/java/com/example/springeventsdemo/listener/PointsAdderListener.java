package com.example.springeventsdemo.listener;

import com.example.springeventsdemo.domain.User;
import com.example.springeventsdemo.event.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
// import org.springframework.scheduling.annotation.Async; // 可选：如果需要异步，保持此注解
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
// @Order(20) // 可选：如果需要控制顺序，保持此注解
public class PointsAdderListener {

    // 添加 condition 属性，使用 SpEL 表达式
    @EventListener(condition = "#event.user.email.endsWith('@example.com')") // <-- 条件！
    @Async // 可选：保持异步
    public void handleUserRegistrationForPoints(UserRegisteredEvent event) {
        // (可选) 为了调试，可以先判断并打印条件是否满足
        boolean conditionMet = event.getUser().getEmail().endsWith("@example.com");

        String threadName = Thread.currentThread().getName(); // 获取当前线程名
        System.out.println();
        // 在日志中明确标出条件检查结果和线程名
        System.out.println("===== PointsAdderListener 开始处理 (线程: " + threadName + ", 条件满足: " + conditionMet + ") =====");

        // --- 注意：下面的逻辑只有在 condition 为 true 时才会被 Spring 调用 ---

        User user = event.getUser();
        System.out.println("事件信息: 用户 " + user.getName() + " 已注册。 (邮箱符合条件，处理积分)");
        System.out.println("事件来源: " + event.getSource().getClass().getSimpleName());

        // 模拟耗时操作（如果保持异步）
        /*
        if (conditionMet) { // 在方法内部不再需要这个 if，因为 Spring 已经处理了条件
            try {
                System.out.println("操作: (异步) 正在为用户 " + user.getName() + " 增加积分 (模拟耗时 2 秒)... 😴");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                 System.err.println("PointsAdderListener 线程被中断");
                 Thread.currentThread().interrupt();
            }
        }
        */
        // 模拟增加积分
        System.out.println("操作: 模拟为 " + user.getName() + " 增加积分成功！");

        System.out.println("===== PointsAdderListener 处理完毕 (线程: " + threadName + ") =====");
        System.out.println();
    }
}