package com.example.carrent.webtoken;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * Service class for handling JWT tokens.
 */
@Service
public class JwtService {

    // The secret key used for JWT token generation and verification
    private static final String SECRET = "638CBE3A90E0303BF3808F40F95A7F02A24B4B5D029C954CF553F79E9EF1DC0384BE681C249F1223F6B55AA21DC070914834CA22C8DD98E14A872CA010091ACC";

    // The validity period of the token (30 minutes)
    private static final long VALIDITY = TimeUnit.MINUTES.toMillis(30);

    /**
     * Generates a JWT token for the specified user details.
     * 
     * @param userDetails The user details.
     * @return The JWT token.
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .signWith(generateKey())
                .compact();
    }

    /**
     * Generates a secret key for token signing.
     * 
     * @return The secret key.
     */
    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    /**
     * Extracts the username from the JWT token.
     * 
     * @param jwt The JWT token.
     * @return The username.
     */
    public String extractUsername(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getSubject();
    }

    /**
     * Parses and verifies the JWT token.
     * 
     * @param jwt The JWT token.
     * @return The claims extracted from the token.
     */
    private Claims getClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    /**
     * Checks if the JWT token is valid (not expired).
     * 
     * @param jwt The JWT token.
     * @return True if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
}
