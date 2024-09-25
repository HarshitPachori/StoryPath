package com.example.storypath_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthCheckController {

    @GetMapping("/health-check")
    public Map<String,String> healthCheck() {
        Map<String,String> map = new HashMap<>();
        map.put("status","up and running");
        return map;
    }

    @GetMapping
    public String server() {
        return "Welcome , Server is now running !!";
    }
}
