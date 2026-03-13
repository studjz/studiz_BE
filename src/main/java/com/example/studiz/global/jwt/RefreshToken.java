package com.example.studiz.global.jwt;


import com.example.studiz.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@RedisHash(value = "refreshToken", timeToLive = 604800)

public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String refreshToken;

    public RefreshToken(User user, String refreshToken) {
        this.user = user;
        this.refreshToken = refreshToken;
    }
    public void updateToken(String newToken) {
        this.refreshToken = newToken;
    }



}
