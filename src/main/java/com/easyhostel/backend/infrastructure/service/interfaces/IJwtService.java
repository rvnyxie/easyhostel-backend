package com.easyhostel.backend.infrastructure.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

/**
 * Interface for JWT Service
 *
 * @author Nyx
 */
public interface IJwtService {

    /**
     * Generate access token
     *
     * @param userDetails UserDetail's object
     * @return Access token
     * @author Nyx
     */
    String generateToken(UserDetails userDetails);

    /**
     * Generate access token with specified claim
     *
     * @param extraClaims Specified claim
     * @param userDetails UserDetail's object
     * @return Access token
     * @author Nyx
     */
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    /**
     * Check if token is valid
     *
     * @param token Token
     * @param userDetails UserDetail's object
     * @return true if valid, false if invalid
     * @author Nyx
     */
    boolean isTokenValid(String token, UserDetails userDetails);

    /**
     * Get expiration time in millisecond from token
     *
     * @return Expiration time in millisecond
     * @author Nyx
     */
    long getExpirationTime();

    /**
     * Extract username from token
     *
     * @param token Token
     * @return username extracted
     * @author Nyx
     */
    String extractUsername(String token);
    
}
