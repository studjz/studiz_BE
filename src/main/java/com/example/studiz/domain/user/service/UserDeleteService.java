package com.example.studiz.domain.user.service;


import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDeleteService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public void delete(String token) {
        String authHeader = token;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authHeader = authHeader.substring(7);
        }


        Long id = jwtProvider.getSubject(authHeader);

        userRepository.deleteById(id);
    }
}
