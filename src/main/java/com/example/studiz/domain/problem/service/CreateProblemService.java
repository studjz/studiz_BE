package com.example.studiz.domain.problem.service;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.presentation.dto.request.CreateProblemRequset;
import com.example.studiz.domain.problem.repository.ProblemRepository;
import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.domain.userproblem.repository.UserProblemRepository;
import com.example.studiz.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateProblemService {

    private final ProblemRepository problemRepository;


    public ResponseEntity<Problem> addProblem(CreateProblemRequset createProblemRequset) {
        Problem problem = Problem.builder()
                .problemSubject(createProblemRequset.getProblemSubject())
                .answerOne(createProblemRequset.getAnswerOne())
                .answerTwo(createProblemRequset.getAnswerTwo())
                .answerThree(createProblemRequset.getAnswerThree())
                .answerFour(createProblemRequset.getAnswerFour())
                .correctAnswer(createProblemRequset.getCorrectAnswer().trim())
                .build();
        Problem savedProblem = problemRepository.save(problem);

        return ResponseEntity.ok(savedProblem);

    }

}
