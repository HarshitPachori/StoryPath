package com.example.storypath_backend.service;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String body);
}
