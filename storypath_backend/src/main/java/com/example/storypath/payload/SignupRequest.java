package com.example.storypath.payload;

public record SignupRequest(
        String username,
        String email,
        String password) {
}
