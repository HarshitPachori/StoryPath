package com.example.storypath.payload;

import lombok.Builder;

@Builder
public record VerifyEmailRequest(String email, String otp) {
}
