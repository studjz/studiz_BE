package com.example.studiz.domain.user.service;


import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsernameChangeService {
    private final UserRepository userRepository;

    public void changeUsername(String username) {

    }
}
