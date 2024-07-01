package com.example.storypath.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

  @GetMapping("/health-check")
  public String healthCheck() {
    return "OK";
  }

  @GetMapping
  public String server() {
    return "Welcome , Server is now running !!";
  }
}
