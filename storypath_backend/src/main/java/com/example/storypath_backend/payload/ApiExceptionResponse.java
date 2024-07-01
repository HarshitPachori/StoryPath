package com.example.storypath_backend.payload;

public record ApiExceptionResponse(
                boolean success,
                String message) {
}
