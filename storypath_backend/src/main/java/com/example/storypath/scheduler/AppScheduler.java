package com.example.storypath.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.storypath.model.JournalEntry;
import com.example.storypath.model.User;
import com.example.storypath.payload.Sentiment;
import com.example.storypath.service.EmailService;
import com.example.storypath.service.UserService;
import com.example.storypath.service.impl.SentimentAnalysisService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppScheduler {

    private final SentimentAnalysisService sentimentAnalysisService;
    private final EmailService emailService;
    private final UserService userService;

    private static String getString(Sentiment sentiment) {
        String positiveTip = "Continue focusing on the positive aspects of your life!";
        String negativeTip = "Consider using prompts to explore challenges in more depth.";
        return switch (sentiment) {
            case POSITIVE, HIGHLY_POSITIVE -> positiveTip;
            case NEGATIVE, HIGHLY_NEGATIVE -> negativeTip;
            default ->
                    "Continue focusing in writing more of such journal writing and explore positive aspects of your life.";
        };
    }

    //    every week 9am in the morning on sunday
    @Scheduled(cron = "0 0 9 * * SUN")
    @Transactional
    public void doSentimentAnalysisAndSendEmail() {
        List<User> userForSentimentAnalysis = userService.getUserForSentimentAnalysis();
        for (User user : userForSentimentAnalysis) {
           String sevenDaysContent = getJournalEntriesOf7Days(user);
            // String sevenDaysContent = "I have been working for all day and become more tired from monday to friday working days. It is worst experience for me. Yesterday I got an email from one organisation for the job. This company provides huge benefits and more things to their customers and employees, and thus I decided to go out to this company and spend time there by networking with more peoples.";
            if (sevenDaysContent.isEmpty()) continue;
            Sentiment sentiment = sentimentAnalysisService.doSentimentAnalysis(sevenDaysContent);
            sendSentimentAnalysisEmail(sentiment, user);
            System.out.println(sentiment);
            log.info("sentiment {}", sentiment);
        }
    }

    private String getJournalEntriesOf7Days(User user) {
        return user.getJournalEntries().stream()
                .filter((journalEntry) -> journalEntry
                        .getDate()
                        .isAfter(LocalDateTime.now().minusDays(7)))
                .map(JournalEntry::getContent).collect(Collectors.joining(" "));
    }

    private void sendSentimentAnalysisEmail(Sentiment sentiment, User user) {
        String tip = getString(sentiment);
        String body = """
                <p>Good Morning,</p>
                <p>Dear %s,</p>
                <p>Here is the Weekly Sentiment Analysis of your journals.</p>
                <p>This week, your journal's overall sentiment came in as <b>%s</b>.<p/>
                <p><b>Tip for next Week : </b> %s</p>
                <p>We hope this report is insightful! Keep writing more journals every week, and feel free to reach out if you have any questions about the report or the Journal App.</p>
                <p>Stay tuned for next week report.</p>
                <p>Warm regards,</p>
                <p>Team Journal App</p>
                """.formatted(user.getUsername(), sentiment.toString(), tip);
        log.info("email : {}", body);
        emailService.sendEmail("harshit7pachori@gmail.com", "Weekly Sentiment Analysis Report", body);
    }
}
