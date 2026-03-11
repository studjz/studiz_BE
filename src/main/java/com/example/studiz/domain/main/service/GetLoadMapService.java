package com.example.studiz.domain.main.service;

import com.example.studiz.domain.main.LoadMap;
import com.example.studiz.domain.main.repository.LoadMapRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetLoadMapService {
    private LoadMapRepository loadMapRepository;

    public LoadMap GetLoadMapService(Long id) {

        return loadMapRepository.findById(id)
                .orElseThrow(()=> new )

    }

}
