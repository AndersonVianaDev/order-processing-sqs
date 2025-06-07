package com.anderson.product_api.infra.exception;

public class InvalidStockOperationException extends RuntimeException{
    public InvalidStockOperationException(String message) {
        super(message);
    }
}
