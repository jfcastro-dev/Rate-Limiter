package com.ratelimiter.ratelimiter.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RateLimiterService {

    private final ConcurrentHashMap<String, Integer> limits = new ConcurrentHashMap<>();

    public boolean tryConsumeToken(String clientKey) {
        // Get the current limit for the client
        Integer remainingLimit = limits.get(clientKey);

        // If the client has no limit set or has exceeded the limit, return false
        if (remainingLimit == null || remainingLimit <= 0) {
            return false;
        }

        // Decrease the limit and return true
        limits.put(clientKey, remainingLimit - 1);
        return true;
    }

    public int getRemainingLimit(String clientKey) {
        // Get the current limit for the client
        Integer remainingLimit = limits.get(clientKey);

        // If the client has no limit set, return 0
        if (remainingLimit == null) {
            return 0;
        }

        return remainingLimit;
    }
}