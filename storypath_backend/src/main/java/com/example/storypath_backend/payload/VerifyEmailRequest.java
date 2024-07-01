package com.example.storypath_backend.payload;

import lombok.Builder;

@Builder
public record VerifyEmailRequest(String email, String otp) {
}
