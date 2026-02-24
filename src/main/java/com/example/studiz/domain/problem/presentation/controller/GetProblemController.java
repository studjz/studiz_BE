package com.example.studiz.domain.problem.presentation.controller;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.service.GetProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/problem")
public class GetProblemController {

    final GetProblemService getProblemService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Problem> get(@PathVariable("id") Long id) {
        Problem problem = getProblemService.getProblemById(id);
        return ResponseEntity.ok(problem);
    }
}
