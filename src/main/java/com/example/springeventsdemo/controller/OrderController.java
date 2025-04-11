package com.example.springeventsdemo.controller;

import com.example.springeventsdemo.domain.Order;
import com.example.springeventsdemo.dto.OrderRequest; // 导入 DTO
import com.example.springeventsdemo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity; // 用于构造 HTTP 响应
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; // 用于接收请求体
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 标记为 RESTful 控制器
@RequestMapping("/api/orders") // 定义基础请求路径
@RequiredArgsConstructor // Lombok 自动生成构造函数注入 final 字段
public class OrderController {

    private final OrderService orderService; // 注入订单服务

    /**
     * 处理创建新订单的 POST 请求。
     * 请求体需要包含 OrderRequest 格式的 JSON 数据。
     * @param request 包含订单信息的请求体 DTO
     * @return 包含创建的订单信息的 ResponseEntity
     */
    @PostMapping // 映射 HTTP POST 请求到 /api/orders
    public ResponseEntity<Order> createOrderApi(@RequestBody OrderRequest request) { // @RequestBody 将 JSON 请求体映射到 DTO
        System.out.println("\n===== OrderController: 收到创建订单请求 =====");
        System.out.println("请求体内容: " + request); // 打印收到的请求数据

        try {
            // 调用服务层方法创建订单
            Order createdOrder = orderService.createOrder(
                    request.getProductId(),
                    request.getQuantity(),
                    request.getAmount(),
                    request.getUserId()
            );
            System.out.println("===== OrderController: 订单创建成功，返回响应 =====");
            // 返回 200 OK 状态码和创建的订单信息
            return ResponseEntity.ok(createdOrder);
        } catch (Exception e) {
            // 简单的异常处理，实际应用中可能需要更复杂的错误处理
            System.err.println("OrderController: 创建订单时发生错误: " + e.getMessage());
            // 返回 500 Internal Server Error 状态码
            return ResponseEntity.internalServerError().build();
        }
    }
}