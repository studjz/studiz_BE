package com.example.studiz.domain.main.presentation.controller;

import com.example.studiz.domain.main.LoadMap;
import com.example.studiz.domain.main.service.GetLoadMapService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/main")
public class GetLoadMapController {

    private final GetLoadMapService getLoadMapService;

    @GetMapping("/loadmap/{id}")
    public ResponseEntity<LoadMap> GetLoadMap(@PathVariable Long id){
    LoadMap loadMap = getLoadMapService.GetLoadMapService(id);
    return ResponseEntity.ok(loadMap);
    }

}
