package com.example.storypath.payload;


import lombok.Builder;


@Builder
public record SignupResponse(
        boolean isSuccess,
        String message,
        boolean isVerified) {
}

