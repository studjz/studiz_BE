package com.example.studiz.domain.user.presentation.controller;

import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.presentation.dto.request.AuthRequest;
import com.example.studiz.domain.user.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@Getter
@Builder
@AllArgsConstructor
@RequestMapping("/user")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/majors")
    public ResponseEntity<List<String>> getMajors() {
        List<String> majors = authService.getMajorList();
        return ResponseEntity.ok(majors);
    }

    @PostMapping("/auth")
    public ResponseEntity<User> auth(@RequestBody @Valid AuthRequest authRequest) {
        User user= authService.createUser(authRequest);
        return ResponseEntity.ok().body(user);
    }
}
