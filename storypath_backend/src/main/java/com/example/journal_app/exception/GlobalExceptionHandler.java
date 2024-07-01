package com.example.storypath.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.storypath.payload.ApiExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ApiExceptionResponse> apiExceptionHandler(ApiException ex) {
    ApiExceptionResponse response = new ApiExceptionResponse(false, ex.getMessage());
    return new ResponseEntity<>(response, ex.getStatus());
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ApiExceptionResponse> generalExceptionHandler(RuntimeException ex) {
    ApiExceptionResponse response = new ApiExceptionResponse(false, ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
