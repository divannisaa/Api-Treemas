package com.treemaswebapi.treemaswebapi.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

@Service
public class JwtService {
    @Value("$2a$10$Fd7hDbC/fjU.QFVpaCcMB.LtdHm0I9fl7QyJk38YgNS4w/j/gqzum") // Read secret from configuration
    private String secret;

    @Value("3600000") // Read token expiration from configuration
    private long expirationMs;
    
   public String generateToken(String nik, String namaKaryawan, String androidId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("nik", nik);
        claims.put("namaKaryawan", namaKaryawan);
        claims.put("deviceId", androidId);

        Key signingKey = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(signingKey)
                .compact();
    }
    public Claims validateTokenAndGetClaims(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Check token expiration
            Date now = new Date();
            if (claims.getExpiration().before(now)) {
                throw new IllegalArgumentException("Token has expired.");
            }

            return claims;
        } catch (Exception e) {
            // Handle token validation errors, e.g., invalid signature or token format
            throw new IllegalArgumentException("Invalid token.");
        }
    
}
}

