package com.example.studiz.domain.problem.presentation.controller;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.presentation.dto.request.AnswerRequest;
import com.example.studiz.domain.problem.service.SolveProblemService;
import com.example.studiz.domain.problem.service.SolveWrongProblemService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Getter
@Builder
@AllArgsConstructor
@RequestMapping("/problem")
public class SolveProblemController {

    private final SolveProblemService solveProblemService;



    @PostMapping("/solve/{id}/check")
    public ResponseEntity<String> solve(
            @PathVariable("id") Long id,
            @RequestBody AnswerRequest answerRequest,
            @RequestHeader("Authorization") String token
    ) {

        boolean isCorrect = solveProblemService.checkAnswer(token, id,answerRequest.getUserAnswer());

        if (isCorrect){
            return ResponseEntity.ok("Correct");
        }
        else  {
            return ResponseEntity.ok("Wrong");
        }
    }


}
