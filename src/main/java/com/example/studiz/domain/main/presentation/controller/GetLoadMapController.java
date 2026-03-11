package com.example.studiz.domain.main.presentation.controller;

import com.example.studiz.domain.main.LoadMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class GetLoadMapController {


    @GetMapping("/loadmap/{id}")
    public ResponseEntity<LoadMap> GetLoadMap(@PathVariable("id") Long id){

    }

}
