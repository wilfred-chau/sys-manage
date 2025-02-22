package com.example.sysmanage.exception;

import lombok.Getter;

/**
 * 全局异常处理
 */
@Getter
public class BusinessException extends RuntimeException {
    private final String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }
} 