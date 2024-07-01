package com.example.storypath_backend.payload;

import lombok.Builder;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record JournalEntryResponse(ObjectId id, String title, String content, LocalDateTime date, List<String> tags) {
}
