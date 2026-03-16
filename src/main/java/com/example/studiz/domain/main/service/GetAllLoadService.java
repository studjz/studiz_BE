package com.example.studiz.domain.main.service;

import com.example.studiz.domain.main.LoadMap;
import com.example.studiz.domain.main.repository.LoadMapRepository;
import com.example.studiz.global.error.exception.CustomException;
import com.example.studiz.global.error.exception.ErrorCode;
import com.example.studiz.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetAllLoadService {

    private final LoadMapRepository loadMapRepository;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public List<LoadMap> getLoadMap(String token) {
        // 1. 토큰 자체가 비어있는지 먼저 확인
        if (token == null || token.isEmpty()) {
            throw new CustomException(ErrorCode.No_Access_Token);
        }

        // 2. 헤더에서 추출 시도
        String accessToken = jwtProvider.getTokenFromHeader(token);

        // 3. 추출된 결과가 null인지 확인 (여기가 74번 줄 에러의 직접적 원인일 가능성이 높음)
        if (accessToken == null) {
            throw new CustomException(ErrorCode.No_Access_Token);
        }

        String userMajor = jwtProvider.getMajorFromToken(accessToken);

        return loadMapRepository.findAllByMajor(userMajor);
    }
}
