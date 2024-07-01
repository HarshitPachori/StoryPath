package com.example.storypath.payload;

public record ApiExceptionResponse(
        boolean success,
        String message) {
}
