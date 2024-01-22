package com.ratelimiter.ratelimiter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;

@EnableAutoConfiguration
@Configuration
public class RedisConfig {

    @Value("${REDIS_HOST:localhost}")
    private String redisHost;
    
    @Bean
    public JedisPool jedisPool() {
        return new JedisPool(redisHost, 6379);
    }
}
