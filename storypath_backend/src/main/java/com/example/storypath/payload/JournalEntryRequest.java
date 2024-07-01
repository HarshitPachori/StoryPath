package com.example.storypath.payload;

import java.util.Collections;
import java.util.List;

public record JournalEntryRequest(String title, String content, List<String> tags) {
    public JournalEntryRequest(String title, String content, List<String> tags) {
        this.title = title;
        this.content = content;
        this.tags = tags != null ? tags : Collections.emptyList();
    }
}
