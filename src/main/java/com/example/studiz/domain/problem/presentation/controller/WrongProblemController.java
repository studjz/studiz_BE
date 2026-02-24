package com.example.studiz.domain.problem.presentation.controller;

import com.example.studiz.domain.problem.presentation.dto.request.RetryAnswerRequest;
import com.example.studiz.domain.problem.presentation.dto.response.WrongProblemResponse;
import com.example.studiz.domain.problem.service.GetWrongProblemService;
import com.example.studiz.domain.problem.service.SolveWrongProblemService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Getter
@Builder
@AllArgsConstructor
@RequestMapping("/problem")
public class WrongProblemController {

    private final GetWrongProblemService getWrongProblemService;
    private final SolveWrongProblemService solveWrongProblemService;


    @GetMapping("/wrong")
    public ResponseEntity<List<WrongProblemResponse>> wrong(@RequestHeader("Authorization") String token){

        List<WrongProblemResponse> wrongProblems = getWrongProblemService.getWrongProblem(token);
        return ResponseEntity.ok(wrongProblems);

    }

    @PostMapping("/wrong/solve")
    public ResponseEntity<String> retryProblem(@RequestHeader("Authorization") String token, @RequestBody RetryAnswerRequest requestDto){
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(401).body("인증 토큰이 없습니다.");
        }
        boolean isCorrect = solveWrongProblemService.solveAllWrongProblems(token, requestDto);

        if (isCorrect) {
            // 정답일 경우 (DB에서 isCorrect가 true로 업데이트됨)
            return ResponseEntity.ok("정답입니다! 오답 노트에서 처리되었습니다.");
        } else {
            // 여전히 오답일 경우
            return ResponseEntity.ok("틀렸습니다. 다시 한 번 생각해 보세요!");
        }
    }


}
