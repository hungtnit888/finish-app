package com.example.base.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider {

    // Constants for claim names
    private static final String AUTHORITIES_CLAIM = "auth";
    private static final String FINGERPRINT_HASH_CLAIM = "fph"; // Fingerprint Hash

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    private Key key;
    private MessageDigest sha256Digest;

    @PostConstruct
    public void init() {
        try {
            this.sha256Digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            log.error("Failed to initialize SHA-256 digest", e);
            throw new RuntimeException("Failed to initialize SHA-256 digest", e);
        }

        if (jwtSecret == null || jwtSecret.length() < 32) {
            log.error("JWT secret key is not configured or too short (requires at least 32 characters). Generating a temporary key.");
            this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        } else {
            byte[] keyBytes = jwtSecret.getBytes();
            this.key = Keys.hmacShaKeyFor(keyBytes);
        }
    }

    // Helper method to hash the fingerprint
    private String hashFingerprint(String fingerprint) {
        if (fingerprint == null || fingerprint.isEmpty()) {
            return null; // Or handle as appropriate
        }
        byte[] hashBytes = sha256Digest.digest(fingerprint.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    // Modified generateToken to include fingerprint hash
    public String generateToken(Authentication authentication, String fingerprint) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // Hash the fingerprint before putting it in the token
        String fingerprintHash = hashFingerprint(fingerprint);

        JwtBuilder builder = Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim(AUTHORITIES_CLAIM, authorities)
                .setIssuedAt(now)
                .setExpiration(expiryDate);

        // Only add fingerprint hash claim if it's not null
        if (fingerprintHash != null) {
             builder.claim(FINGERPRINT_HASH_CLAIM, fingerprintHash);
        }

        return builder.signWith(key, SignatureAlgorithm.HS256).compact();
    }

    // Overloaded generateToken without fingerprint (phiên bản gốc)
    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim(AUTHORITIES_CLAIM, authorities)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Get all claims from token
    private Claims getClaimsFromToken(String token) {
         return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Lấy username từ token
    public String getUsernameFromJWT(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    // Lấy fingerprint hash từ token claims
    public String getFingerprintHashFromToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return claims.get(FINGERPRINT_HASH_CLAIM, String.class);
        } catch (Exception e) {
            log.error("Could not get fingerprint hash from token", e);
            return null;
        }
    }

    // Chỉ validate chữ ký và thời hạn
    public boolean validateTokenSignatureAndExpiration(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature: {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token: {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty: {}", ex.getMessage());
        }
        return false;
    }

    // Validate fingerprint hash từ token với fingerprint từ request
    public boolean validateFingerprint(String tokenFingerprintHash, String requestFingerprint) {
        if (tokenFingerprintHash == null) {
            log.warn("Token does not contain a fingerprint hash.");
            return false;
        }
        String requestFingerprintHash = hashFingerprint(requestFingerprint);
        if (requestFingerprintHash == null) {
            log.warn("Request is missing the fingerprint header.");
            return false;
        }
        boolean matches = tokenFingerprintHash.equals(requestFingerprintHash);
        if (!matches) {
             log.warn("Fingerprint hash mismatch. Token_Hash={}, Request_Hash={}", tokenFingerprintHash, requestFingerprintHash);
        }
        return matches;
    }
} 