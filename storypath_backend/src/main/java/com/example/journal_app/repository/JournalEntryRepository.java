package com.example.storypath.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.storypath.model.JournalEntry;

import java.util.List;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

    List<JournalEntry> findAllByUserId(ObjectId userId);

    JournalEntry findByUserIdAndId(ObjectId userId, ObjectId journalId);
}
