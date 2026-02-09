package com.example.studiz.domain.problem.presentation;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.presentation.dto.request.CreateProblemRequset;
import com.example.studiz.domain.problem.service.ProblemService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
@Builder
@AllArgsConstructor
public class ProblemController {

    private ProblemService problemService;

    @PostMapping("/problem/create")
    public ResponseEntity<Problem> create(@RequestBody CreateProblemRequset createProblemRequset) {
        return problemService.addProblem(createProblemRequset);
    }

//    @GetMapping("/problem/solve")
//    public ResponseEntity<Problem> solve() {
//
//    }
}
