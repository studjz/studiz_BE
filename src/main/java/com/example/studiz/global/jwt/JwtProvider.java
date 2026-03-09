package com.example.studiz.global.jwt;


import com.example.studiz.domain.user.Role;
import io.jsonwebtoken.Claims;
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
    private final String jwtSecret;
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

    public String createAccessToken(Long id, Role role, String major) {
        return Jwts.builder()
                .setSubject(id.toString())
                .claim("major",major)
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


    public String getTokenFromHeader(String header) {
        String token = header;
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }
        return token;
    }

    public Long getSubject(String token) {
        return Long.parseLong(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject());

    }
    public String getMajorFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)      // 내 서버의 키로 서명 확인
                .build()
                .parseClaimsJws(token)   // 토큰 해석 시작
                .getBody();              // 내용물(Payload) 가져오기

        return claims.get("major", String.class); // "major" 키의 값을 String으로 리턴
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
