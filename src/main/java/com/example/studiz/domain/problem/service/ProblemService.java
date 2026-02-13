package com.example.studiz.domain.problem.service;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.presentation.dto.request.CreateProblemRequset;
import com.example.studiz.domain.problem.repository.ProblemRepository;
import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.domain.userproblem.UserProblem;
import com.example.studiz.domain.userproblem.repository.UserProblemRepository;
import com.example.studiz.global.jwt.JwtProvider;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProblemService {
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final UserProblemRepository userProblemRepository;
    private final JwtProvider jwtProvider;


    public ResponseEntity<Problem> addProblem(CreateProblemRequset createProblemRequset) {
        Problem problem = Problem.builder()
                .problemSubject(createProblemRequset.getProblemSubject())
                .answerOne(createProblemRequset.getAnswerOne())
                .answerTwo(createProblemRequset.getAnswerTwo())
                .answerThree(createProblemRequset.getAnswerThree())
                .answerFour(createProblemRequset.getAnswerFour())
                .correctAnswer(createProblemRequset.getCorrectAnswer())
                .build();
        Problem savedProblem = problemRepository.save(problem);

        return ResponseEntity.ok(savedProblem);

    }

    public Problem getProblemById(Long id) {
        return problemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("문제를 찾을수 없습니다"));
    }

    @Transactional
    public boolean checkAnswer(String token, Long  problemId ,String userAnswer) {
        String authHeader = token;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authHeader = authHeader.substring(7);
        }


        Long id = jwtProvider.getSubject(authHeader);


        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new IllegalArgumentException("문제를 찾을 수 없습니다."));

        boolean isCorrect =problem.getCorrectAnswer().trim().equals(userAnswer);

        UserProblem history = userProblemRepository.findByUserAndProblem(user,problem)
                .orElse(UserProblem.builder()
                        .user(user)
                        .problem(problem)
                        .build());

        history.updateResult(isCorrect);
        userProblemRepository.save(history);

        refreshUserStats(user);

        return isCorrect;
    }

    private void refreshUserStats(User user) {
        long totalProblemCount = problemRepository.count();
        long mySolvedCount = userProblemRepository.countByUser(user);
        long myCorrectCount = userProblemRepository.countByUserAndIsCorrectTrue(user);

        double progressRate = (totalProblemCount == 0) ? 0 : (double) mySolvedCount / totalProblemCount * 100;

        double correctRate = (mySolvedCount == 0) ? 0 : (double) myCorrectCount / mySolvedCount * 100;

        progressRate = Math.round(progressRate * 10.0) / 10.0;
        correctRate = Math.round(correctRate * 10.0) / 10.0;

        user.updateStatus(progressRate, correctRate); // Dirty Checking에 의해 자동 업데이트
    }
}
