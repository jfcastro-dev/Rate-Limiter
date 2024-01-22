package com.ratelimiter.ratelimiter.dto;

public class RateLimitDataDTO {

    private String endpoint;
    private int limit;
    private int remaining;
    private long resetTime;

    // Getters and setters

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public long getResetTime() {
        return resetTime;
    }

    public void setResetTime(long resetTime) {
        this.resetTime = resetTime;
    }
}