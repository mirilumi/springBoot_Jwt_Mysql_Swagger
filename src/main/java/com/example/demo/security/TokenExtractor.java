package com.example.demo.security;

public interface TokenExtractor {
    public String extract(String payload);
}
