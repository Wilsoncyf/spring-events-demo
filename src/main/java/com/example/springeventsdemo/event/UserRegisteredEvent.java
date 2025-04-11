package com.example.springeventsdemo.event;

import com.example.springeventsdemo.domain.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter // Lombok 注解: 为 'user' 字段生成 getter 方法
public class UserRegisteredEvent extends ApplicationEvent {

    private final User user; // 与事件关联的用户对象

    /**
     * 创建一个新的 UserRegisteredEvent 事件实例。
     *
     * @param source 发布事件的组件 (通常是 'this')。
     * @param user   已注册的用户数据。
     */
    public UserRegisteredEvent(Object source, User user) {
        super(source); // 调用父类构造函数，传递事件源
        System.out.println("调试信息: UserRegisteredEvent 已创建。 Source: "
                           + source.getClass().getSimpleName()
                           + ", User: " + user.getName()
                           + ", Timestamp: " + this.getTimestamp()); // 添加了时间戳日志
        this.user = user;
    }

    // Lombok 的 @Getter 会自动为我们生成 getUser() 方法
}