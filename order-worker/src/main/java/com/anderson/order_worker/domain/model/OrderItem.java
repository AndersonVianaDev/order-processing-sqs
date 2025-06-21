package com.anderson.order_worker.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    private UUID productId;

    private String productName;

    private BigDecimal productPrice;

    private BigDecimal total;

    private Integer quantity;

    public BigDecimal calculateSubtotal() {
        return productPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
