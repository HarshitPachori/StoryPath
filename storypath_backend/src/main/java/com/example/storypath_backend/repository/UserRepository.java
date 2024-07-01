package com.example.storypath_backend.repository;

import com.example.storypath_backend.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    List<User> findByWantsSentimentAnalysisTrue();

}
