package com.example.storypath_backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class TransactionManagementConfig {
    private final MongoDatabaseFactory mongoDatabaseFactory;

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new MongoTransactionManager(mongoDatabaseFactory);
    }
}
