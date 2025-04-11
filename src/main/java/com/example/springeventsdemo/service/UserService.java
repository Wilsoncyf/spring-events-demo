package com.example.springeventsdemo.service;

import com.example.springeventsdemo.domain.User;
import com.example.springeventsdemo.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor; // Lombok 注解: 为 final 字段生成构造函数
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void registerUser(String name, String email) throws RuntimeException { // 明确抛出 RuntimeException 以触发回滚
        System.out.println("\nUserService (Tx): 正在处理用户注册: " + name);
        User newUser = new User(name, email);
        // userRepository.save(newUser); // 模拟保存
        System.out.println("UserService (Tx): 用户数据已保存 (模拟)。");

        UserRegisteredEvent registrationEvent = new UserRegisteredEvent(this, newUser);
        System.out.println("UserService (Tx): 正在发布 UserRegisteredEvent (在事务中)...");
        this.eventPublisher.publishEvent(registrationEvent); // 事件在事务内部发布
        System.out.println("UserService (Tx): 事件已发布。");

        // 3. 模拟事务中后续操作可能失败
        if (name.contains("失败君")) { // 让特定名字触发失败
            System.out.println("UserService (Tx): 检测到 '失败君', 模拟事务后续操作失败，准备抛出异常...");
            throw new RuntimeException("模拟事务失败!"); // 抛出运行时异常，默认会触发事务回滚 ❌
        }

        System.out.println("UserService (Tx): 用户注册流程事务性完成 (准备提交): " + name);
        // 如果没有异常，事务将在此处准备提交 ✅
    }
}