package com.example.studiz.domain.main.presentation.controller;

import com.example.studiz.domain.main.LoadMap;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class GetLoadController {


    @GetMapping
    public List<LoadMap> getLoadMap() {

    }
}
