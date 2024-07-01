package com.example.storypath.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.storypath.exception.ApiException;
import com.example.storypath.model.JournalEntry;
import com.example.storypath.model.User;
import com.example.storypath.payload.JournalEntryRequest;
import com.example.storypath.payload.JournalEntryResponse;
import com.example.storypath.repository.JournalEntryRepository;
import com.example.storypath.repository.UserRepository;
import com.example.storypath.service.JournalEntryService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class JournalEntryServiceImpl implements JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public JournalEntryResponse createJournalEntry(JournalEntryRequest journalEntryRequest) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            log.info("username is {}  :   ", username);
            User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found with email " + username));
            JournalEntry journalEntry = JournalEntry.builder()
                    .title(journalEntryRequest.title())
                    .content(journalEntryRequest.content())
                    .tags(journalEntryRequest.tags())
                    .user(user)
                    .date(LocalDateTime.now())
                    .build();

            JournalEntry savedEntry = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(savedEntry);
            userRepository.save(user);
            return JournalEntryResponse.builder()
                    .id(savedEntry.getId())
                    .title(savedEntry.getTitle())
                    .content(savedEntry.getContent())
                    .tags(savedEntry.getTags())
                    .date(savedEntry.getDate())
                    .build();
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public JournalEntry getEntryById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEntryById(ObjectId id) {
        JournalEntry journalEntry = getEntryById(id);
        journalEntryRepository.delete(journalEntry);
    }
}
