package com.example.springeventsdemo.listener;

import com.example.springeventsdemo.event.UserRegisteredEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase; // 1. 导入事务阶段
import org.springframework.transaction.event.TransactionalEventListener; // 2. 导入注解

// import org.springframework.core.annotation.Order;
// import org.springframework.scheduling.annotation.Async;

@Component
// @Order(10) // 可选
public class EmailNotificationListener /* 不再需要实现接口 */ {

    // 3. 使用 @TransactionalEventListener，默认监听 AFTER_COMMIT
    @TransactionalEventListener // 默认 phase = TransactionPhase.AFTER_COMMIT
    // @Async // 事务性监听器也可以是异步的
    public void handleUserRegistrationCompletion(UserRegisteredEvent event) {
        System.out.println();
        System.out.println("===== EmailNotificationListener (@TransactionalEventListener - AFTER_COMMIT) 开始处理 =====");
        System.out.println("事件信息: 用户 " + event.getUser().getName() + " 注册事务已成功提交。");
        System.out.println("操作: 正在发送欢迎邮件给 " + event.getUser().getEmail() + "...");
        // sendEmailService.sendWelcomeEmail(event.getUser().getEmail());
        System.out.println("操作: 模拟邮件发送成功！");
        System.out.println("===== EmailNotificationListener (AFTER_COMMIT) 处理完毕 =====");
        System.out.println();
    }

    // 4. (可选) 添加一个监听事务回滚的方法
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleUserRegistrationRollback(UserRegisteredEvent event) {
        System.out.println();
        System.out.println("===== EmailNotificationListener (@TransactionalEventListener - AFTER_ROLLBACK) 开始处理 =====");
        System.out.println("事件信息: 用户 " + event.getUser().getName() + " 的注册事务已回滚！");
        System.out.println("操作: 不应发送欢迎邮件。可能需要执行一些清理或补偿操作。");
        // logRollback(event.getUser());
        System.out.println("===== EmailNotificationListener (AFTER_ROLLBACK) 处理完毕 =====");
        System.out.println();
    }
}