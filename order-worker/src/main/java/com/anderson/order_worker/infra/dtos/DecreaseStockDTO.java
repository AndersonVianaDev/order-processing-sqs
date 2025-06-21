package com.anderson.order_worker.infra.dtos;

public record DecreaseStockDTO(Integer quantity) {
    public static DecreaseStockDTO from(Integer quantity) {
        return new DecreaseStockDTO(quantity);
    }
}
