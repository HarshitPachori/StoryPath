package com.example.storypath.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "journal_entries")
public class JournalEntry {

    @Id
    private ObjectId id;
    
    @DBRef
    private User user;

    @NonNull
    private String title;

    private String content;
    private LocalDateTime date;

    @Builder.Default
    private List<String> tags = new ArrayList<>();
}
