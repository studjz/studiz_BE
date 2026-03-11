package com.example.studiz.domain.main.presentation.controller;

import com.example.studiz.domain.main.LoadMap;
import com.example.studiz.domain.main.service.GetAllLoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class GetAllLoadController {
    private final GetAllLoadService getAllLoadService;

    @GetMapping("/loadmap")
    public ResponseEntity<List<LoadMap>> getLoadMap(String token) {
        List<LoadMap> loadMap = getAllLoadService.getLoadMap(token);
        return ResponseEntity.ok().body(loadMap);
    }
}
