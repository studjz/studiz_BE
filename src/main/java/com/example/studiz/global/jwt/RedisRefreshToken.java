package com.example.studiz.global.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = 604800)
public class RedisRefreshToken {

    @Id
    private Long userId; // 이 ID가 Redis의 키가 됩니다 (예: refreshToken:1)

    private String refreshToken;

    public void updateToken(String newToken) {
        this.refreshToken = newToken;
    }
}