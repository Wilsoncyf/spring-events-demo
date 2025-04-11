package com.example.springeventsdemo.service;

import com.example.springeventsdemo.domain.User;
import com.example.springeventsdemo.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor; // Lombok 注解: 为 final 字段生成构造函数
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service // 标记此类为 Spring 的服务 Bean
@RequiredArgsConstructor // Lombok: 为 final 字段生成构造函数 (用于依赖注入)
public class UserService {

    // final 字段确保通过 Lombok 生成的构造函数进行注入
    private final ApplicationEventPublisher eventPublisher;

    /**
     * 注册用户并发布事件的方法。
     * @param name  用户名
     * @param email 邮箱
     */
    public void registerUser(String name, String email) {
        System.out.println("UserService: 正在处理用户注册: " + name);

        // 模拟核心业务逻辑：例如保存用户信息到数据库...
        System.out.println("UserService: 用户数据已保存 (模拟)。");
        User newUser = new User(name, email);

        // 1. 创建事件实例
        UserRegisteredEvent registrationEvent = new UserRegisteredEvent(this, newUser); // 'this' 指当前的 UserService 实例
        System.out.println("UserService: 正在创建 UserRegisteredEvent 实例。");

        // 2. 发布事件
        System.out.println("UserService: 正在通过 ApplicationEventPublisher 发布事件...");
        this.eventPublisher.publishEvent(registrationEvent); // ✨ 发布事件！

        System.out.println("UserService: 事件已发布。用户注册流程完成: " + name);
    }
}