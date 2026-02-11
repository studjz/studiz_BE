package com.example.studiz.domain.problem.presentation.controller;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.presentation.dto.request.AnswerRequest;
import com.example.studiz.domain.problem.presentation.dto.request.CreateProblemRequset;
import com.example.studiz.domain.problem.service.ProblemService;
import com.example.studiz.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Getter
@Builder
@AllArgsConstructor
@RequestMapping("/problem")
public class ProblemController {

    private final ProblemService problemService;



    @PostMapping("/create")
    public ResponseEntity<Problem> create(@RequestBody CreateProblemRequset createProblemRequset) {
        return problemService.addProblem(createProblemRequset);
    }

    @GetMapping("/solve/{id}")
    public ResponseEntity<Problem> get(@PathVariable("id") Long id) {
    Problem problem =problemService.getProblemById(id);
    return ResponseEntity.ok(problem);
    }
    @PostMapping("/solve/{id}/check")
    public ResponseEntity<String> solve(
            @PathVariable("id") Long id,
            @RequestBody AnswerRequest answerRequest,
            @RequestHeader("Authorization") String token
    ) {

        boolean isCorrect =problemService.checkAnswer(token, id,answerRequest.getUseranswer());

        if (isCorrect){
            return ResponseEntity.ok("Correct");
        }
        else  {
            return ResponseEntity.ok("Wrong");
        }
    }

}
