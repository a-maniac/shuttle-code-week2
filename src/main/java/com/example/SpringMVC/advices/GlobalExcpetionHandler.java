package com.example.SpringMVC.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExcpetionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleEmployeeNotFound(){
        ApiError apiError=ApiError.builder().status(HttpStatus.NOT_FOUND).message("Resource not Found").build();
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);

    }
}
