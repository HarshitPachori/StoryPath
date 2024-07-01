package com.example.storypath.service;

import org.bson.types.ObjectId;

import com.example.storypath.model.User;
import com.example.storypath.payload.JournalEntryRequest;
import com.example.storypath.payload.JournalEntryResponse;
import com.example.storypath.payload.UserDto;

import java.util.List;

public interface UserService {
    UserDto getProfile(String jwtToken);

    UserDto updateProfile(String jwtToken);

    List<User> getUserForSentimentAnalysis();

    List<JournalEntryResponse> getAllJournalEntriesOfUser(String jwtToken);

    JournalEntryResponse updateJournalEntryOfUser(String jwtToken, ObjectId journalId, JournalEntryRequest request);
}
