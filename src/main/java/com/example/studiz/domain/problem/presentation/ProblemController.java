package com.example.studiz.domain.problem.presentation;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.presentation.dto.request.AnswerRequest;
import com.example.studiz.domain.problem.presentation.dto.request.CreateProblemRequset;
import com.example.studiz.domain.problem.service.ProblemService;
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
public class ProblemController {

    private ProblemService problemService;

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
    public ResponseEntity<String> solve(@PathVariable("id") Long id,
                                         @RequestBody AnswerRequest answerRequest) {
        boolean iscorrect = problemService.checkAnswer(id, answerRequest.getUseranswer());

        if (iscorrect) {
            return ResponseEntity.ok().body("정답입니다");
        }
        else {
            return  ResponseEntity.ok("틀렸습니다");
        }
    }

}
