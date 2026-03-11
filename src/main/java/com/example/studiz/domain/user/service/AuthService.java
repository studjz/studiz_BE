package com.example.studiz.domain.user.service;

import com.example.studiz.domain.user.MajorType;
import com.example.studiz.domain.user.Role;
import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.presentation.dto.request.AuthRequest;
import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.global.error.exception.CustomException;
import com.example.studiz.global.error.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final BCryptPasswordEncoder passwordEncoder;


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
}
