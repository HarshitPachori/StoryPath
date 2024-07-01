package com.example.journal_app;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.storypath.model.JournalEntry;
import com.example.storypath.model.User;
import com.example.storypath.repository.JournalEntryRepository;
import com.example.storypath.repository.UserRepository;
import com.example.storypath.scheduler.AppScheduler;
import com.example.storypath.service.impl.AuthServiceImpl;

import java.util.List;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AppScheduler appScheduler;
    @Autowired
    AuthServiceImpl authService;

    @Autowired
    JournalEntryRepository journalEntryRepository;

    @Test
    void contextLoads() {
        List<User> byWantsSentimentAnalysis = userRepository.findByWantsSentimentAnalysisTrue();
        byWantsSentimentAnalysis
                .forEach((item) -> System.out.println(item.getEmail()));

    }

    @Test
    void testMethod() {
        // appScheduler.doSentimentAnalysisAndSendEmail();
        // authService.sendVerificationEmail("harshitpachori",
        // "harshit7pachori@gmail.com", "456789");
        ObjectId userId = new ObjectId("667aa325d7fa9e60a149b52a");
        List<JournalEntry> byUserId = journalEntryRepository.findAllByUserId(userId);
        System.out.println(byUserId.getFirst().getContent());
        Assertions.assertFalse(byUserId.isEmpty());
    }

    @Test
    void testMethod1() {
        ObjectId userId = new ObjectId("667aa325d7fa9e60a149b52a");
        ObjectId journalId = new ObjectId("667c06762a19bc0a1e32aca1");
        JournalEntry journalEntry = journalEntryRepository.findByUserIdAndId(userId, journalId);
        System.out.println(journalEntry.getContent());
        Assertions.assertNotNull(journalEntry);
    }

}
