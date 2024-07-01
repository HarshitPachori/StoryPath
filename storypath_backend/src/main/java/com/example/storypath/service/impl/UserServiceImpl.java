package com.example.storypath.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.storypath.exception.ApiException;
import com.example.storypath.model.JournalEntry;
import com.example.storypath.model.User;
import com.example.storypath.payload.JournalEntryRequest;
import com.example.storypath.payload.JournalEntryResponse;
import com.example.storypath.payload.UserDto;
import com.example.storypath.repository.JournalEntryRepository;
import com.example.storypath.repository.UserRepository;
import com.example.storypath.service.UserService;
import com.example.storypath.utils.JwtTokenHelper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JournalEntryRepository journalEntryRepository;
    private final JwtTokenHelper tokenHelper;


    @Override
    public UserDto getProfile(String token) {
        User userProfile = getUserFromJwtToken(token);
        return UserDto.builder()
                .id(userProfile.getId())
                .email(userProfile.getEmail())
                .name(userProfile.getUsername())
                .wantsSentimentAnalysis(true)
                .roles(userProfile.getRoles())
                .build();
    }

    @Override
    public UserDto updateProfile(String jwtToken) {
        User user = getUserFromJwtToken(jwtToken);
        user.setWantsSentimentAnalysis(!user.isWantsSentimentAnalysis());
        User userProfile = userRepository.save(user);
        return UserDto.builder()
                .id(userProfile.getId())
                .email(userProfile.getEmail())
                .name(userProfile.getUsername())
                .wantsSentimentAnalysis(!userProfile.isWantsSentimentAnalysis())
                .roles(userProfile.getRoles())
                .build();
    }

    public User getUserFromJwtToken(String jwtToken) {
        String username = tokenHelper.getUsernameFromToken(jwtToken);
        log.info("Fetching user profile from email: {}", username);
        return userRepository.findByEmail(username).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found with email " + username));
    }

    @Override
    public List<User> getUserForSentimentAnalysis() {
        return userRepository.findByWantsSentimentAnalysisTrue();
    }

    @Override
    public List<JournalEntryResponse> getAllJournalEntriesOfUser(String jwtToken) {
        User user = getUserFromJwtToken(jwtToken);
        return user.getJournalEntries().stream()
                .map(journalEntry -> JournalEntryResponse.builder()
                        .id(journalEntry.getId())
                        .title(journalEntry.getTitle())
                        .content(journalEntry.getContent())
                        .date(journalEntry.getDate())
                        .tags(journalEntry.getTags())
                        .build()).toList();
    }

    @Override
    public JournalEntryResponse updateJournalEntryOfUser(String jwtToken, ObjectId journalId, JournalEntryRequest request) {
        User user = getUserFromJwtToken(jwtToken);
        JournalEntry journalByUser = journalEntryRepository.findByUserIdAndId(user.getId(), journalId);
        journalByUser.setTitle(request.title() != null && !request.title().isEmpty() ? request.title() : journalByUser.getTitle());
        journalByUser.setContent(request.content() != null && !request.content().isEmpty() ? request.content() : journalByUser.getContent());
        journalByUser.setTags(request.tags() != null && !request.tags().isEmpty() ? request.tags() : journalByUser.getTags());
        JournalEntry savedJournalEntry = journalEntryRepository.save(journalByUser);
        return JournalEntryResponse.builder()
                .id(savedJournalEntry.getId())
                .title(savedJournalEntry.getTitle())
                .content(savedJournalEntry.getContent())
                .tags(savedJournalEntry.getTags())
                .date(savedJournalEntry.getDate())
                .build();
    }

}