package com.example.storypath.controller;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.storypath.payload.JournalEntryRequest;
import com.example.storypath.payload.JournalEntryResponse;
import com.example.storypath.payload.UserDto;
import com.example.storypath.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {
        UserDto userProfile = userService.getProfile(jwtToken);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Profile fetched successfully");
        response.put("data", userProfile);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<Map<String, Object>> updateProfile(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {
        UserDto userUpdateProfile = userService.updateProfile(jwtToken);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Profile updated successfully");
        response.put("data", userUpdateProfile);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/journals")
    public ResponseEntity<Map<String, Object>> getAllJournalEntries(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {
        List<JournalEntryResponse> allEntries = userService.getAllJournalEntriesOfUser(jwtToken);
        HashMap<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", allEntries.isEmpty() ? "Don't have any journals yet. Create one please."
                : "All journals fetched successfully");
        response.put("data", allEntries);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/journals/{id}")
    public ResponseEntity<Map<String, Object>> updateUserJournal(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken,
            @RequestBody JournalEntryRequest request,
            @PathVariable("id") ObjectId journalId) {
        JournalEntryResponse journalEntryResponse = userService.updateJournalEntryOfUser(jwtToken, journalId, request);
        HashMap<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Journal Entry updated successfully");
        response.put("data", journalEntryResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
