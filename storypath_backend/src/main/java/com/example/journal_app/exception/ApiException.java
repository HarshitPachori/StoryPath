package com.example.storypath.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
  HttpStatus status;
  String message;

  public ApiException(HttpStatus status, String message) {
    super(message);
    this.status = status;
    this.message = message;
  }
}
