package com.example.studiz.domain.problem.service;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.presentation.dto.request.RetryAnswerRequest;
import com.example.studiz.domain.problem.repository.ProblemRepository;
import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.domain.userproblem.UserProblem;
import com.example.studiz.domain.userproblem.repository.UserProblemRepository;
import com.example.studiz.global.jwt.JwtProvider;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class SolveWrongProblemService {
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final UserProblemRepository userProblemRepository;
    private final JwtProvider jwtProvider;


    @Transactional
    public boolean solveAllWrongProblems(String token, RetryAnswerRequest retryAnswerRequest) {
        String authHeader = token;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authHeader = authHeader.substring(7);
        }

        Long id = jwtProvider.getSubject(authHeader);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        UserProblem userProblem = userProblemRepository.findByUserAndProblem_ProblemId(user,retryAnswerRequest.getProblemId())
                .orElseThrow(()-> new IllegalArgumentException("해당문제에 대한 기록이 없습니다."));


        Problem problem = userProblem.getProblem();
        boolean isAnswerCorrect = problem.getCorrectAnswer().equals(retryAnswerRequest.getUserAnswer());



        if (isAnswerCorrect) {
            userProblem.markAsCorrect();

        }
        return isAnswerCorrect;
    }

}
