package com.example.storypath_backend.payload;

import lombok.Builder;
import org.bson.types.ObjectId;

import java.util.List;

@Builder
public record UserDto(
                ObjectId id,
                String name,
                String email,
                boolean wantsSentimentAnalysis,
                List<String> roles) {
}
