package com.example.storypath_backend.service.impl;

import com.example.storypath_backend.exception.ApiException;
import com.example.storypath_backend.model.JournalEntry;
import com.example.storypath_backend.model.User;
import com.example.storypath_backend.payload.JournalEntryRequest;
import com.example.storypath_backend.payload.JournalEntryResponse;
import com.example.storypath_backend.repository.JournalEntryRepository;
import com.example.storypath_backend.repository.UserRepository;
import com.example.storypath_backend.service.JournalEntryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
