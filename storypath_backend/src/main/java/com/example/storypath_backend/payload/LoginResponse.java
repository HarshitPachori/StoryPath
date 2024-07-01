package com.example.storypath_backend.payload;

import lombok.Builder;

@Builder
public record LoginResponse(
                boolean isSuccess,
                String message,
                String accessToken,
                boolean isVerified) {
}
