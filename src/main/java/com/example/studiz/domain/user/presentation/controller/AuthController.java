package com.example.studiz.domain.user.presentation.controller;

import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.presentation.dto.request.AuthRequest;
import com.example.studiz.domain.user.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
@Builder
@AllArgsConstructor
@RequestMapping("/user")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<User> auth(@RequestBody @Valid AuthRequest authRequest) {
        User user= authService.createUser(authRequest);
        return ResponseEntity.ok().body(user);
    }
}
