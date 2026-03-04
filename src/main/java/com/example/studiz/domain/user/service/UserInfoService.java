package com.example.studiz.domain.user.service;

import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public User userInfo(String token) {
        String authHeader = jwtProvider.getTokenFromHeader(token);

        Long id = jwtProvider.getSubject(authHeader);

        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 ID(" + id + ")를 가진 사용자가 존재하지 않습니다."));
    }
}