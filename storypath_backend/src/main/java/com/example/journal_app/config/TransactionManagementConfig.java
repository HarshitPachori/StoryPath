package com.example.storypath.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.RequiredArgsConstructor;

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
