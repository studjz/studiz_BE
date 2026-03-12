package com.example.studiz.domain.main.service;

import com.example.studiz.domain.main.LoadMap;
import com.example.studiz.domain.main.repository.LoadMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLoadMapService {
    private final LoadMapRepository loadMapRepository;

    public LoadMap GetLoadMapService(Long id) {

     LoadMap loadMap = loadMapRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 로드맵을 찾을수 없습니다."));

     return loadMap;
    }

}