package com.example.studiz.domain.main.presentation.controller;

import com.example.studiz.domain.main.LoadMap;
import com.example.studiz.domain.main.presentation.dto.MakeLoadRequest;
import com.example.studiz.domain.main.repository.LoadMapRepository;
import com.example.studiz.domain.main.service.CreateLoadService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/main")
public class CreateLoadController {

    private CreateLoadService createLoadService;

    @PostMapping("/create/load")
    public ResponseEntity<LoadMap> createLoad(@RequestBody @Valid MakeLoadRequest makeLoadRequest){
        LoadMap loadMap = createLoadService.createLoadMap(makeLoadRequest);
        return ResponseEntity.ok().body(loadMap);

    }

}
