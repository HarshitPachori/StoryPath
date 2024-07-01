package com.example.storypath_backend.repository;

import com.example.storypath_backend.model.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

    List<JournalEntry> findAllByUserId(ObjectId userId);

    JournalEntry findByUserIdAndId(ObjectId userId, ObjectId journalId);
}
