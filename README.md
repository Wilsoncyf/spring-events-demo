# Spring 应用事件机制演示 (Spring Events Demo)

本项目旨在学习和演示 Spring Boot 框架中的应用事件机制 (Application Event Mechanism)。

## 项目目标

* 理解 Spring 事件的核心概念 (ApplicationEvent, ApplicationListener, ApplicationEventPublisher, ApplicationContext)。
* 实践如何创建、发布和监听自定义事件。
* 探索同步事件和异步事件处理。
* 了解 `@EventListener` 注解的用法。
* (未来可能添加事务性事件等内容)

## 当前进度

* [x] **创建自定义事件:** 定义了 `UserRegisteredEvent`。 (Lesson 1.3)
* [x] **发布事件:** 在 `UserService` 中注入 `ApplicationEventPublisher` 并发布 `UserRegisteredEvent`。 (Lesson 1.3)
* [x] **创建监听器 (接口):** 使用 `ApplicationListener<UserRegisteredEvent>` 接口创建了 `EmailNotificationListener` 来响应事件。 (Lesson 1.4)
* [x] **触发演示:** 使用 `CommandLineRunner` 在应用启动时触发用户注册流程。

## 如何运行

1.  克隆仓库: `git clone https://github.com/Wilsoncyf/spring-events-demo.git`
2.  进入项目目录: `cd spring-events-demo`
3.  使用 Maven 运行: `./mvnw spring-boot:run` (或者在 IDE 中直接运行 `SpringEventsDemoApplication.java`)
4.  观察控制台输出，可以看到事件发布和监听器处理的日志。

## 技术栈

* Java 17
* Spring Boot 3.4.4
* Maven
* Lombok