package com.example.studiz.domain.main.presentation.controller;

import com.example.studiz.domain.main.LoadMap;
import com.example.studiz.domain.main.service.GetLoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class GetLoadController {
    private final GetLoadService getLoadService;

    @GetMapping("/loadmap")
    public ResponseEntity<List<LoadMap>> getLoadMap(String token) {
        List<LoadMap> loadMap = getLoadService.getLoadMap(token);
        return ResponseEntity.ok().body(loadMap);
    }
}
