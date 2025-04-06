package com.example.base.service;

import com.example.base.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class CacheService {

    private final Map<String, String> dataStore = new HashMap<>();

    @Cacheable(value = RedisConfig.APPLICATION_CACHE, key = "#id")
    public String getData(String id) {
        log.info("Cache miss for id: {}", id);
        // Simulate slow database call
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return dataStore.computeIfAbsent(id, k -> UUID.randomUUID().toString());
    }

    @CachePut(value = RedisConfig.APPLICATION_CACHE, key = "#id")
    public String updateData(String id, String data) {
        log.info("Updating data for id: {}", id);
        dataStore.put(id, data);
        return data;
    }

    @CacheEvict(value = RedisConfig.APPLICATION_CACHE, key = "#id")
    public void deleteData(String id) {
        log.info("Deleting data for id: {}", id);
        dataStore.remove(id);
    }

    @CacheEvict(value = RedisConfig.APPLICATION_CACHE, allEntries = true)
    public void clearCache() {
        log.info("Clearing all cache entries");
        dataStore.clear();
    }
} 