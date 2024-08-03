package com.example.storypath_backend.service;

import com.example.storypath_backend.model.User;
import com.example.storypath_backend.payload.JournalEntryRequest;
import com.example.storypath_backend.payload.JournalEntryResponse;
import com.example.storypath_backend.payload.UserDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {
    UserDto getProfile(String jwtToken);

    UserDto updateProfile(String jwtToken);

    List<User> getUserForSentimentAnalysis();

    List<JournalEntryResponse> getAllJournalEntriesOfUser(String jwtToken);

    JournalEntryResponse updateJournalEntryOfUser(String jwtToken, ObjectId journalId, JournalEntryRequest request);
}
