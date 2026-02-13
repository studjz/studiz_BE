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
public class SelectUserMajorService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public User seleteUserMajor(String token, String major) {
        String authHeader = token;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authHeader = authHeader.substring(7);
        }

        Long id = jwtProvider.getSubject(authHeader);

        User user = userRepository.findById(id)
                .orElseThrow(()->  new NoSuchElementException("해당유저를 못찾는다"));

        user.updateMajor(major);
        return userRepository.save(user);

    }

}
