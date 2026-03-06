package com.example.studiz.domain.main.service;

import com.example.studiz.domain.main.LoadMap;
import com.example.studiz.domain.main.presentation.dto.MakeLoadRequest;
import com.example.studiz.domain.main.repository.LoadMapRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Builder
@RequiredArgsConstructor
@Transactional
public class CreateLoadService {
    private final LoadMapRepository loadMapRepository;

    public LoadMap createLoadMap(MakeLoadRequest makeLoadRequest) {
        LoadMap loadMap = LoadMap.builder()
                .mapSubject(makeLoadRequest.getMapSubject())
                .mapText(makeLoadRequest.getMapText())
                .major(makeLoadRequest.getMapMajor())
                .link(makeLoadRequest.getLink())
                .build();
    loadMapRepository.save(loadMap);
    return loadMap;

    }

}
