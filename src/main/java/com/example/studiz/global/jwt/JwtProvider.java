package com.example.studiz.global.jwt;


import com.example.studiz.domain.user.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private String jwtSecret;
    private final Long expirationTime;
    private final Long refreshTokenExpiration;
    private Key key;

    public JwtProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.access-expiration}") Long expirationTime,
            @Value("${jwt.refresh-expiration}") Long refreshTokenExpiration
    ) {
        this.jwtSecret = secretKey;
        this.expirationTime = expirationTime;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    @PostConstruct
    public void init() {this.key= Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));}

    public String createAccessToken(Long id, Role role) {
        return Jwts.builder()
                .setSubject(id.toString())
                .claim("role",role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Boolean validateToken(String token) {
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Long getSubject(String token) {
        return Long.parseLong(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject());

    }
    public String getRole(String token) {
        return Jwts.parserBuilder().build().parseClaimsJws(token).getBody().get("role").toString();
    }


    public String createRefreshToken(String schoolId) {
        return Jwts.builder()
                .setSubject(schoolId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+refreshTokenExpiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

}
