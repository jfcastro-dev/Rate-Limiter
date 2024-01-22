package com.ratelimiter.ratelimiter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ratelimiter.ratelimiter.service.RateLimiterService;

@RestController
@RequestMapping("/ratelimiter")
public class RateLimiterController {

    // Assume you have a RateLimiterService that handles the rate limiting logic
    private final RateLimiterService rateLimiterService;

    public RateLimiterController(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @PostMapping("/")
    public ResponseEntity<String> defaultRequest(@RequestParam String clientId) {
        return ResponseEntity.ok("Access to API is Granted.");
    }
}
