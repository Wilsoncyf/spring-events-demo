package com.example.springeventsdemo.dto;

import lombok.Data; // Lombok: 包含 @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
import java.math.BigDecimal;

/**
 * 用于接收创建订单请求的数据传输对象 (DTO)。
 */
@Data // 使用 Lombok @Data 简化代码
public class OrderRequest {
    private String productId;   // 产品 ID
    private int quantity;       // 数量
    private BigDecimal amount;  // 订单金额
    private String userId;      // 用户 ID
    // 注意：确保字段名与将要发送的 JSON 请求体中的键名一致
}