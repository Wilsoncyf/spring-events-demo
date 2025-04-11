package com.example.springeventsdemo.listener;

import com.example.springeventsdemo.domain.User; // 导入 User 类
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 一个直接监听 POJO (User 对象) 的监听器示例。
 */
@Component
public class PojoListener {

    // @EventListener 注解的方法，参数类型直接是 User
    @EventListener
    public void handleUserPojoEvent(User userPayload) { // <-- 参数就是 POJO 本身！
        System.out.println();
        System.out.println("===== PojoListener 开始处理 =====");
        System.out.println("监听器信息: 收到了一个 User 类型的 POJO 事件!");
        System.out.println("负载信息 (Payload): 用户名=" + userPayload.getName() + ", 邮箱=" + userPayload.getEmail());

        // 注意：当监听器参数直接是 POJO 时，
        // 你无法在这个方法签名中直接获取事件的 source 或 timestamp。
        // 你只得到了核心数据 payload。

        System.out.println("操作: (PojoListener) 可以根据用户信息执行一些操作...");
        System.out.println("===== PojoListener 处理完毕 =====");
        System.out.println();
    }

    /*
    // 덜 흔한 대안: 만약 source나 timestamp 정보가 필요하다면,
    // PayloadApplicationEvent<User> 래퍼 자체를 리스닝할 수도 있습니다.
    // 不太常用的替代方案：如果确实需要 source 或 timestamp 信息，
    // 也可以选择监听 PayloadApplicationEvent<User> 这个包装器本身。
    @EventListener
    public void handleUserPayloadWrapper(org.springframework.context.PayloadApplicationEvent<User> event) {
        User userPayload = event.getPayload();
        Object source = event.getSource();
        long timestamp = event.getTimestamp();
        System.out.println("收到 PayloadApplicationEvent: User=" + userPayload.getName() + ", Source=" + source + ", Timestamp=" + timestamp);
    }
    */
}