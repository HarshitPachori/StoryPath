package com.example.storypath.payload;

public record LoginRequest(
        String email,
        String password) {
}
