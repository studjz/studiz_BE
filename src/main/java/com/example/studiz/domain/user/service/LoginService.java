package com.example.studiz.domain.user.service;

import com.example.studiz.global.error.exception.CustomException;
import com.example.studiz.global.error.exception.ErrorCode;
import com.example.studiz.global.jwt.JwtProvider;
import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.global.jwt.dto.response.TokenResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public TokenResponse login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.Wrong_Id_Password));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new CustomException(ErrorCode.Wrong_Id_Password);
        }

        String accessToken = jwtProvider.createAccessToken(user.getId() ,user.getRole(),user.getUserMajor());

        String refreshToken = jwtProvider.createRefreshToken(String.valueOf(user.getId()));
        return new TokenResponse(accessToken,refreshToken);
    }
}