package com.example.storypath_backend.service;

import com.example.storypath_backend.payload.LoginRequest;
import com.example.storypath_backend.payload.LoginResponse;
import com.example.storypath_backend.payload.SignupRequest;
import com.example.storypath_backend.payload.SignupResponse;

import java.util.Map;

public interface AuthService {
    LoginResponse loginUser(LoginRequest loginRequest);

    SignupResponse signupUser(SignupRequest signupRequest);

    Map<String, Object> verifyAccount(String email, String otp);
}
