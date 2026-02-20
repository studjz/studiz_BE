package com.example.studiz.domain.problem.service;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.presentation.dto.request.CreateProblemRequset;
import com.example.studiz.domain.problem.presentation.dto.request.RetryAnswerRequest;
import com.example.studiz.domain.problem.presentation.dto.response.WrongProblemResponse;
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

import java.util.List;
import java.util.stream.Collectors;

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
                .correctAnswer(createProblemRequset.getCorrectAnswer().trim())
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

        String storedAnswer = problem.getCorrectAnswer() != null ? problem.getCorrectAnswer().trim() : "";
        String inputAnswer = userAnswer != null ? userAnswer.trim() : "";

        boolean isCorrect = storedAnswer.equals(inputAnswer);

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

    @Transactional(readOnly = true)
    public List<WrongProblemResponse> getWrongProblem(String token) {

        String authHeader = token;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authHeader = authHeader.substring(7);
        }

        Long id = jwtProvider.getSubject(authHeader);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        List<UserProblem> wrongHistories = userProblemRepository.findAllByUserAndIsCorrectFalse(user);

        return wrongHistories.stream()
                .map(userProblem -> {
                    Problem problem = userProblem.getProblem();
                    return new WrongProblemResponse(
                            problem.getProblemId(),
                            problem.getProblemSubject(),
                            problem.getAnswerOne(),
                            problem.getAnswerTwo(),
                            problem.getAnswerThree(),
                            problem.getAnswerFour()
                    );
                })
                .collect(Collectors.toList());

    }

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
        // (주의: getCorrectAnswer()는 실제 Problem 엔티티의 정답 필드명에 맞게 수정하세요)

        // 4. 정답을 맞췄다면 상태 업데이트 (다음부터 getWrongProblem 조회 시 안 나오게 됨)
        if (isAnswerCorrect) {
            userProblem.markAsCorrect();
            // JPA의 Dirty Checking(변경 감지) 덕분에 save()를 명시적으로 호출하지 않아도 DB에 반영됩니다.
        }
        // 5. 채점 결과 반환 (클라이언트에서 정답/오답 여부를 알 수 있도록)
        return isAnswerCorrect;
    }

}
