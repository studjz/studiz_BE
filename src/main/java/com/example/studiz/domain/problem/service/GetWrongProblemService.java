package com.example.studiz.domain.problem.service;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.presentation.dto.response.WrongProblemResponse;
import com.example.studiz.domain.problem.repository.ProblemRepository;
import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.domain.userproblem.UserProblem;
import com.example.studiz.domain.userproblem.repository.UserProblemRepository;
import com.example.studiz.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GetWrongProblemService {
    private final UserRepository userRepository;
    private final UserProblemRepository userProblemRepository;
    private final JwtProvider jwtProvider;


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

}
