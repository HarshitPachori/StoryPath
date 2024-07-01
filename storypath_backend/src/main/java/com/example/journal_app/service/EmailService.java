package com.example.storypath.service;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String body);
}
