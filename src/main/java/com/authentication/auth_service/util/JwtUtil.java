package com.authentication.auth_service.util;

import com.authentication.auth_service.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey; // Base64-encoded secret from application.properties

    @Value("${jwt.expiration}") // in milliseconds
    private long jwtExpiration;

    /**
     * Generate JWT token containing email, role, and userId (UUID).
     */
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail()) // Email as subject
                .claim("role", user.getRole().name()) // Store role
                .claim("userId", user.getId().toString()) // Store UUID as string
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extract username (email) from token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract role from token.
     */
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    /**
     * Extract userId (UUID) from token.
     */
    public UUID extractUserId(String token) {
        String userIdStr = extractAllClaims(token).get("userId", String.class);
        return userIdStr != null ? UUID.fromString(userIdStr) : null;
    }

    /**
     * Generic claim extractor.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    /**
     * Validate token against UserDetails.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Check if token is expired.
     */
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    /**
     * Parse all claims.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Decode Base64 secret key.
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
