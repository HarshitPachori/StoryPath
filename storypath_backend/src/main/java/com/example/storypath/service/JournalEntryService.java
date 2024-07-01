package com.example.storypath.service;

import org.bson.types.ObjectId;

import com.example.storypath.model.JournalEntry;
import com.example.storypath.payload.JournalEntryRequest;
import com.example.storypath.payload.JournalEntryResponse;

public interface JournalEntryService {
    JournalEntryResponse createJournalEntry(JournalEntryRequest journalEntryRequest);

    JournalEntry getEntryById(ObjectId id);

    void deleteEntryById(ObjectId id);

}
