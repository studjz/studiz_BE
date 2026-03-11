package com.example.studiz.domain.main.service;

import com.example.studiz.domain.main.LoadMap;
import com.example.studiz.domain.main.repository.LoadMapRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetLoadMapService {
    private LoadMapRepository loadMapRepository;

    public LoadMap GetLoadMapService(Long id) {

     LoadMap loadMap = loadMapRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 로드맵을 찾을수 없습니다."));

     return loadMap;
    }

}