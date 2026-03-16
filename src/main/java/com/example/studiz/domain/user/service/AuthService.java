package com.example.studiz.domain.user.service;

import com.example.studiz.domain.user.MajorType;
import com.example.studiz.domain.user.Role;
import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.presentation.dto.request.AuthRequest;
import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.global.error.exception.CustomException;
import com.example.studiz.global.error.exception.ErrorCode;
import com.example.studiz.global.jwt.JwtProvider;
import com.example.studiz.global.jwt.RefreshToken;
import com.example.studiz.global.jwt.RefreshTokenRepository;
import com.example.studiz.global.jwt.dto.response.TokenResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public List<String> getMajorList() {
        return Arrays.stream(MajorType.values())
                .map(MajorType::getKrName)
                .collect(Collectors.toList());
    }


    public User createUser(AuthRequest authRequest) {
        String username = authRequest.getUsername();
        if(userRepository.existsUserByUsername(username)){
            throw new CustomException(ErrorCode.Duplication_School_Id);
        }
        User user = User.builder()
                .username(username)
                .schoolId(authRequest.getSchoolId())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .role(Role.USER)
                .major(authRequest.getMajor())
                .build();

        userRepository.save(user);
        return user;
    }


    @Transactional
    public TokenResponse reissue(String refreshToken) {

        // 1. Refresh Token 유효성 검증
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Refresh Token이 만료되었거나 유효하지 않습니다.");
        }

        // 2. Refresh Token에서 유저 ID(Subject) 추출
        Long userId = jwtProvider.getSubject(refreshToken);

        // 3. Redis에 저장된 토큰인지 확인 (토큰 탈취 여부 확인)
        RefreshToken savedToken = refreshTokenRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("로그아웃된 사용자이거나 토큰이 존재하지 않습니다."));

        if (!savedToken.getRefreshToken().equals(refreshToken)) {
            throw new RuntimeException("토큰 정보가 일치하지 않습니다.");
        }

        // 4. 새로운 Access Token 발급을 위해 유저 정보 조회 (MySQL)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        // 5. 새로운 토큰 쌍 생성 (RTR 적용)
        String newAccessToken = jwtProvider.createAccessToken(user.getId(), user.getRole(), user.getMajor());
        String newRefreshToken = jwtProvider.createRefreshToken(user.getId().toString());

        // 6. Redis 업데이트
        savedToken.updateToken(newRefreshToken);
        refreshTokenRepository.save(savedToken);

        return new TokenResponse(newAccessToken, newRefreshToken);
    }
}

