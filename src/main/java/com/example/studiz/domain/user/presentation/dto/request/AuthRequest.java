package com.example.studiz.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthRequest {
    private Long schoolId;
    private String username;
    private String password;
}
