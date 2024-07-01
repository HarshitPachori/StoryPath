package com.example.storypath.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.storypath.payload.*;
import com.example.storypath.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signupHandler(@RequestBody SignupRequest request) {
        SignupResponse response = authService.signupUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginHandler(@RequestBody LoginRequest request) {
        LoginResponse response = authService.loginUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/verify-account")
    public ResponseEntity<Map<String, Object>> verifyAccount(@RequestBody VerifyEmailRequest request) {
        Map<String, Object> response = authService.verifyAccount(request.email(), request.otp());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
