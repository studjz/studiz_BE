package com.example.studiz.domain.problem.service;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.presentation.dto.request.CreateProblemRequset;
import com.example.studiz.domain.problem.repository.ProblemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProblemService {
    private final ProblemRepository problemRepository;


    public ResponseEntity<Problem> addProblem(CreateProblemRequset createProblemRequset) {
        Problem problem = Problem.builder()
                .problemSubject(createProblemRequset.getProblemSubject())
                .answerOne(createProblemRequset.getAnswerOne())
                .answerTwo(createProblemRequset.getAnswerTwo())
                .answerThree(createProblemRequset.getAnswerThree())
                .answerFour(createProblemRequset.getAnswerFour())
                .build();
        Problem savedProblem = problemRepository.save(problem);

        return ResponseEntity.ok(savedProblem);

    }
}
