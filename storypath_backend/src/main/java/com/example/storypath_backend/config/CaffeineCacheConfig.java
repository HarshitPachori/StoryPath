package com.example.storypath_backend.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineCacheConfig {
  @Bean
  public CaffeineCacheManager cacheManager() {
  CaffeineCacheManager cacheManager = new CaffeineCacheManager("users",
  "journals");
  cacheManager.setCaffeine(caffeineCacheBuilder());
  return cacheManager;
  }
  
  Caffeine<Object, Object> caffeineCacheBuilder() {
  return Caffeine.newBuilder()
  .expireAfterAccess(60, TimeUnit.MINUTES)
  .maximumSize(500);
  }
}
