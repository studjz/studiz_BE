package com.example.studiz.domain.user.service;

import com.example.studiz.domain.user.Role;
import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.presentation.dto.request.AuthRequest;
import com.example.studiz.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User createUser(AuthRequest authRequest) {
        String username = authRequest.getUsername();
        if(userRepository.existsUserByUsername(username)){
            throw new IllegalArgumentException("Username already exists");
        }
        User user = User.builder()
                .username(username)
                .schoolId(authRequest.getSchoolId())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return user;
    }
}
