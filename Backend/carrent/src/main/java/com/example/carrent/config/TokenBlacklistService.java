package com.example.carrent.config;

import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

/**
 * Service class for managing token blacklisting.
 */
@Service
public class TokenBlacklistService {

    // Set to store blacklisted tokens
    private Set<String> blacklist = new HashSet<>();

    /**
     * Blacklists a token.
     * 
     * @param token the token to blacklist
     */
    public void blacklistToken(String token) {
        blacklist.add(token);
    }

    /**
     * Checks if a token is blacklisted.
     * 
     * @param token the token to check
     * @return true if the token is blacklisted, false otherwise
     */
    public boolean isTokenBlacklisted(String token) {
        return blacklist.contains(token);
    }
}
