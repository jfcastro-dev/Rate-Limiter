package com.ratelimiter.ratelimiter.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ratelimiter.ratelimiter.service.RateLimiterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class RateLimiterInterceptor implements HandlerInterceptor {

    private final RateLimiterService rateLimiterService;
    private final int MAX_REQUESTS = 10;
    private final int TTL = 5*60; // 5 minutes

    public RateLimiterInterceptor(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String key = request.getRemoteAddr(); // Use IP address as key
        if (!rateLimiterService.tryConsumeToken(key, MAX_REQUESTS, TTL)) { // Allow MAX_REQUESTS per hour
            response.setStatus(429);
            return false;
        }
        return true;
    }
}