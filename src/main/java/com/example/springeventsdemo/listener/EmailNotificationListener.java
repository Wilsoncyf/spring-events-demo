package com.example.springeventsdemo.listener; // 新建 listener 包

import com.example.springeventsdemo.domain.User;
import com.example.springeventsdemo.event.UserRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 一个监听 UserRegisteredEvent 的监听器示例，使用 ApplicationListener 接口。
 */
@Component // <-- 重要！必须将监听器注册为一个 Bean，Spring 才能发现它
public class EmailNotificationListener implements ApplicationListener<UserRegisteredEvent> { // <-- 实现接口并指定监听的事件类型

    @Override
    public void onApplicationEvent(UserRegisteredEvent event) { // <-- 实现事件处理方法
        System.out.println(); // 加个空行，让日志更清晰
        System.out.println("===== EmailNotificationListener 开始处理 ====="); // 标识是哪个监听器在工作

        // 1. 从事件对象中获取需要的数据
        User registeredUser = event.getUser(); // 获取事件携带的用户信息
        Object eventSource = event.getSource(); // 获取发布事件的源对象
        long timestamp = event.getTimestamp(); // 获取事件发生的时间戳

        System.out.println("事件信息: [用户: " + registeredUser.getName() + ", 邮箱: " + registeredUser.getEmail() + "]");
        System.out.println("事件来源: " + eventSource.getClass().getSimpleName()); // 打印事件源的类名
        System.out.println("事件时间戳: " + timestamp);

        // 2. 执行具体的监听逻辑（这里我们模拟发送邮件）
        System.out.println("操作: 正在准备向邮箱 " + registeredUser.getEmail() + " 发送欢迎邮件...");
        // 实际场景下，这里会调用邮件发送服务
        // sendEmailService.sendWelcomeEmail(registeredUser.getEmail());
        System.out.println("操作: 模拟邮件已发送成功！");
        System.out.println("===== EmailNotificationListener 处理完毕 =====");
        System.out.println(); // 加个空行
    }
}