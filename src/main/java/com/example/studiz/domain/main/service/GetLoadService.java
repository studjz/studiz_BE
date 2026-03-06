package com.example.studiz.domain.main.service;

import com.example.studiz.domain.main.repository.LoadMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GetLoadService {
    private final LoadMapRepository loadMapRepository;


}
