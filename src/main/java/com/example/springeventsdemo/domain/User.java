package com.example.springeventsdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter                 // Lombok 注解: 自动生成 getter 方法
@AllArgsConstructor     // Lombok 注解: 自动生成包含所有参数的构造函数
@ToString               // Lombok 注解: 自动生成一个有用的 toString() 方法
public class User {
    private String name;    // 用户名
    private String email;   // 邮箱
}