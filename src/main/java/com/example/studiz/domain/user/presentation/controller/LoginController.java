package com.example.studiz.domain.user.presentation.controller;

import com.example.studiz.global.jwt.dto.response.TokenResponse;
import com.example.studiz.domain.user.presentation.dto.request.LoginRequest;
import com.example.studiz.domain.user.service.LoginService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
       TokenResponse tokenResponse = loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(tokenResponse);
    }

}
