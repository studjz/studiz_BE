package com.example.studiz.domain.problem.presentation.controller;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.presentation.dto.request.AnswerRequest;
import com.example.studiz.domain.problem.presentation.dto.request.CreateProblemRequset;
import com.example.studiz.domain.problem.presentation.dto.request.RetryAnswerRequest;
import com.example.studiz.domain.problem.presentation.dto.response.WrongProblemResponse;
import com.example.studiz.domain.problem.service.ProblemService;
import com.example.studiz.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        boolean isCorrect =problemService.checkAnswer(token, id,answerRequest.getUserAnswer());

        if (isCorrect){
            return ResponseEntity.ok("Correct");
        }
        else  {
            return ResponseEntity.ok("Wrong");
        }
    }
    @GetMapping("/wrong")
    public ResponseEntity<List<WrongProblemResponse>> wrong(@RequestHeader("Authorization") String token){

        List<WrongProblemResponse> wrongProblems = problemService.getWrongProblem(token);
        return ResponseEntity.ok(wrongProblems);

    }

    @PostMapping("/wrong/solve")
    public ResponseEntity<String> retryProblem(@RequestHeader("Authorization") String token, @RequestBody RetryAnswerRequest requestDto){
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(401).body("인증 토큰이 없습니다.");
        }
        boolean isCorrect = problemService.solveAllWrongProblems(token, requestDto);

        if (isCorrect) {
            // 정답일 경우 (DB에서 isCorrect가 true로 업데이트됨)
            return ResponseEntity.ok("정답입니다! 오답 노트에서 처리되었습니다.");
        } else {
            // 여전히 오답일 경우
            return ResponseEntity.ok("틀렸습니다. 다시 한 번 생각해 보세요!");
        }
    }


}
