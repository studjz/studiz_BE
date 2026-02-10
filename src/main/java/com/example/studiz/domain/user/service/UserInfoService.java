package com.example.studiz.domain.user.service;

import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public User userInfo(String token) {
        String authHeader = token;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authHeader = authHeader.substring(7);
        }


        Long id = jwtProvider.getSubject(authHeader);
        User user = userRepository.findBySchoolId(id).get();
        return user;
    }
}