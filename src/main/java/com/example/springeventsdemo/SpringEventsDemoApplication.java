package com.example.springeventsdemo;

import com.example.springeventsdemo.service.UserService; // 导入 UserService
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; // 导入 Bean

@SpringBootApplication
public class SpringEventsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEventsDemoApplication.class, args);
    }

    // 添加这个 Bean 定义
    @Bean
    CommandLineRunner commandLineRunner(UserService userService) { // 注入 UserService
        return args -> {
            System.out.println("CommandLineRunner: 应用已启动, 准备触发用户注册...");
            // 调用服务方法来触事件发布
            userService.registerUser("陈永峰", "chenyongfeng@example.com");
            System.out.println("CommandLineRunner: 用户注册触发完成。");
        };
    }
}