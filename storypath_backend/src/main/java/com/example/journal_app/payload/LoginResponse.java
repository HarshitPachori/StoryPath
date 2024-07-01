package com.example.storypath.payload;

import lombok.Builder;


@Builder
public record LoginResponse(
        boolean isSuccess,
        String message,
        String accessToken,
        boolean isVerified) {
}
