package com.example.storypath_backend.service;

import com.example.storypath_backend.model.JournalEntry;
import com.example.storypath_backend.payload.JournalEntryRequest;
import com.example.storypath_backend.payload.JournalEntryResponse;
import org.bson.types.ObjectId;

public interface JournalEntryService {
    JournalEntryResponse createJournalEntry(JournalEntryRequest journalEntryRequest);

    JournalEntry getEntryById(ObjectId id);

    void deleteEntryById(ObjectId id);

}
