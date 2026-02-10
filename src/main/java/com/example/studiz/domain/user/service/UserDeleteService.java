package com.example.studiz.domain.user.service;


import com.example.studiz.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDeleteService {
    private final UserRepository userRepository;

    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
