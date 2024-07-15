package com.example.SpringMVC.advices;

import lombok.Data;
import org.modelmapper.internal.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private T data;
    private ApiError error;
    private LocalDateTime timestamp;

    public ApiResponse(T data) {
        this(); // without default constructor the timestamp won't be visible
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }
}
