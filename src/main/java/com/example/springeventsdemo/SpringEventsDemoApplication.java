package com.example.springeventsdemo;

import com.example.springeventsdemo.service.UserService; // 导入 UserService
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; // 导入 Bean
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // <-- 2. 添加注解，启用异步支持
public class SpringEventsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEventsDemoApplication.class, args);
    }

    // 添加这个 Bean 定义
    @Bean
    CommandLineRunner commandLineRunner(UserService userService) {
        return args -> {
            System.out.println("CommandLineRunner: 应用已启动, 准备触发事务性用户注册...");

            // 尝试注册一个会成功的用户
            try {
                System.out.println("\n--- 触发注册: 事务预期成功 ---");
                userService.registerUser("成功君", "success@example.com");
            } catch (Exception e) {
                System.err.println("CommandLineRunner: 成功君注册时发生意外错误: " + e.getMessage());
            }

            // 尝试注册一个会失败的用户
            try {
                System.out.println("\n--- 触发注册: 事务预期失败 ---");
                userService.registerUser("失败君", "fail@example.com"); // 这个会抛异常
            } catch (Exception e) {
                System.err.println("CommandLineRunner: 捕获到失败君注册时抛出的异常 (预期事务回滚): " + e.getMessage());
            }

            System.out.println("\nCommandLineRunner: 用户注册触发完成。");
        };
    }
}