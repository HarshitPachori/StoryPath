package com.example.storypath_backend.payload;

import lombok.Builder;

@Builder
public record SignupResponse(
                boolean isSuccess,
                String message,
                boolean isVerified) {
}
