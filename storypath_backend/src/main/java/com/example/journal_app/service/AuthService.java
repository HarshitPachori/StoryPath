package com.example.storypath.service;

import java.util.Map;

import com.example.storypath.payload.LoginRequest;
import com.example.storypath.payload.LoginResponse;
import com.example.storypath.payload.SignupRequest;
import com.example.storypath.payload.SignupResponse;

public interface AuthService {
    LoginResponse loginUser(LoginRequest loginRequest);

    SignupResponse signupUser(SignupRequest signupRequest);

    Map<String, Object> verifyAccount(String email, String otp);
}
