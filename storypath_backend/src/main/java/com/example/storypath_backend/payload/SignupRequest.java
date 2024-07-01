package com.example.storypath_backend.payload;

public record SignupRequest(
                String username,
                String email,
                String password) {
}
