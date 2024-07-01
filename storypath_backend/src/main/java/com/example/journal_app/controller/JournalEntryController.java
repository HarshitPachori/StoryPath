package com.example.storypath.controller;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.storypath.model.JournalEntry;
import com.example.storypath.payload.JournalEntryRequest;
import com.example.storypath.payload.JournalEntryResponse;
import com.example.storypath.service.impl.JournalEntryServiceImpl;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/journal")
@RequiredArgsConstructor
public class JournalEntryController {

    private final JournalEntryServiceImpl journalEntryService;


    @PostMapping
    public ResponseEntity<HashMap<String, Object>> createJournalEntry(@RequestBody JournalEntryRequest journalEntryRequest) {
        JournalEntryResponse journalEntryResponse = journalEntryService.createJournalEntry(journalEntryRequest);
        HashMap<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Journal created successfully");
        response.put("data", journalEntryResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        JournalEntry journalEntry = journalEntryService.getEntryById(myId);
        if (journalEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(journalEntry, HttpStatus.OK);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<Void> deleteJournalEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteEntryById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
