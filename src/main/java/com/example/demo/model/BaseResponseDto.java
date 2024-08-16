package com.example.demo.model;

import lombok.Data;

@Data
public class BaseResponseDto<Object> {
    private String responseCode;
    private String message;
    private Object result;
}
