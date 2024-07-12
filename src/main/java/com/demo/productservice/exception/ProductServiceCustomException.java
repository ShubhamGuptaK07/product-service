package com.demo.productservice.exception;

import lombok.Data;

@Data
public class ProductServiceCustomException extends RuntimeException {
    private String errorCode;
    public ProductServiceCustomException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;

    }
}
