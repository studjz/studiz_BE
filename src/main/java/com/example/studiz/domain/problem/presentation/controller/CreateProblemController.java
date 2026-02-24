package com.example.studiz.domain.problem.presentation.controller;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.presentation.dto.request.CreateProblemRequset;
import com.example.studiz.domain.problem.service.CreateProblemService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
@Builder
@AllArgsConstructor
@RequestMapping("/problem")
public class CreateProblemController {

    private final CreateProblemService createProblemService;

    @PostMapping("/create")
    public ResponseEntity<Problem> create(@RequestBody CreateProblemRequset createProblemRequset) {
        return createProblemService.addProblem(createProblemRequset);
    }

}
