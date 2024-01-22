package com.ratelimiter.ratelimiter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@EnableAutoConfiguration
@Service
public class RateLimiterService {
    @Autowired
    private JedisPool jedisPool;

    public RateLimiterService(@Autowired JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public boolean tryConsumeToken(String key, int max, long ttl) {
        try (Jedis jedis = jedisPool.getResource()) {
            String currentVal = jedis.get(key);
            if (currentVal == null) {
                jedis.setex(key, (int) ttl, "1");
                return true;
            } else if (Integer.parseInt(currentVal) < max) {
                jedis.incr(key); // Increment the count for this IP
                return true;
            } else {
                return false;
            }
        }
    }
}