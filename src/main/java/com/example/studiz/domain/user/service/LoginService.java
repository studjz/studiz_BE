package com.example.studiz.domain.user.service;

import com.example.studiz.global.jwt.JwtProvider;
import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.global.jwt.dto.response.TokenResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    // 반환 타입을 ResponseEntity가 아닌 순수 TokenResponse로 변경합니다.
    public TokenResponse login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("아이디나 비번이 틀렸습니다."));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("아이디나 비번이 틀렸습니다.");
        }

        String accessToken = jwtProvider.createAccessToken(user.getId() ,user.getRole());

        String refreshToken = jwtProvider.createRefreshToken(String.valueOf(user.getId()));
        return new TokenResponse(accessToken,refreshToken);
    }
}