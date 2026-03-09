package com.example.studiz.domain.main.service;

import com.example.studiz.domain.main.LoadMap;
import com.example.studiz.domain.main.repository.LoadMapRepository;
import com.example.studiz.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GetLoadService {

    private final LoadMapRepository loadMapRepository;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public List<LoadMap> getLoadMap(String token) {
        String accessToken = jwtProvider.getTokenFromHeader(token);

        String userMajor = jwtProvider.getMajorFromToken(accessToken);

        List<LoadMap> loadMap = loadMapRepository.findAllByMajor(userMajor);

        return loadMap;
    }
}
