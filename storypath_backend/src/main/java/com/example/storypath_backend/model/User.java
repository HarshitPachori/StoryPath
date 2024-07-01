package com.example.storypath_backend.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private ObjectId id;

    // automatic indexing is not done we have to do in application.yaml
    @Indexed(unique = true)
    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    @Indexed(unique = true)
    private String email;

    private boolean isVerified;

    private boolean wantsSentimentAnalysis;

    private String emailVerificationOtp;

    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Builder.Default
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
