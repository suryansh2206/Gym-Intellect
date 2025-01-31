package com.knowit.gymintellect.gym_intellect.utils;

import java.util.Date;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    // Generate signing key
    private Key getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes();
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
    }

    // Generate expiration date
    private Date getExpirationDate() {
        return new Date((new Date()).getTime() + jwtExpirationMs);
    }

    // Method to generate JWT token
    public String generateJwtToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(getExpirationDate())
                .signWith(getSigningKey())
                .compact();
    }

    // Method to get username from JWT token
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Method to validate JWT token
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
            return false;
        }
    }
}
