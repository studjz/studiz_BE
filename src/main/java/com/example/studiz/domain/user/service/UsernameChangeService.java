package com.example.studiz.domain.user.service;

import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.global.error.exception.CustomException;
import com.example.studiz.global.error.exception.ErrorCode;
import com.example.studiz.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class UsernameChangeService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public User changeUsername(String token,String username) {
        String authHeader = jwtProvider.getTokenFromHeader(token);

        Long id = jwtProvider.getSubject(authHeader);

        User user = userRepository.findById(id)
                .orElseThrow(()->  new CustomException(ErrorCode.User_Not_Found));

        user.updateUsername(username);

        return userRepository.save(user);
    }
}