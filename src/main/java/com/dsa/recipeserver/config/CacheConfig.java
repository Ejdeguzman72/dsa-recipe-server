package com.dsa.recipeserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

    @Autowired
    private CacheManager cacheManager;

    private final String RECIPE_LIST = "recipeList";
    private final String RECIPE_BY_ID = "recipeById";
    private final String RECIPE_PAGINATION_LIST = "recipePaginationList";
    @Bean
    public CacheManager recipeCacheManager() {
        SimpleCacheManager recipeCacheManager = new SimpleCacheManager();
        recipeCacheManager.setCaches(Arrays.asList(
            new ConcurrentMapCache(RECIPE_LIST),
            new ConcurrentMapCache(RECIPE_BY_ID),
            new ConcurrentMapCache(RECIPE_PAGINATION_LIST)
        ));

        return recipeCacheManager;
    }

    @Scheduled(fixedRate = 600)
    public void clearCacheSchedule() {
        for (String name:cacheManager.getCacheNames()) {
            cacheManager.getCache(name).clear();
        }
    }
}