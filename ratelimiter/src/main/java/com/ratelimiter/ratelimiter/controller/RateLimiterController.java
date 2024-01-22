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

    @PostMapping("/consume")
    public ResponseEntity<String> consumeToken(@RequestParam String clientId) {
        boolean allowed = rateLimiterService.tryConsumeToken(clientId);
        if (allowed) {
            return ResponseEntity.ok("Token consumed successfully");
        } else {
            return ResponseEntity.status(429).body("Rate limit exceeded");
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus(@RequestParam String clientId) {
        int remainingLimit = rateLimiterService.getRemainingLimit(clientId);
        return ResponseEntity.ok("Remaining limit: " + remainingLimit);
    }
}
