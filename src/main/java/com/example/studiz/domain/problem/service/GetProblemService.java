package com.example.studiz.domain.problem.service;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.problem.repository.ProblemRepository;
import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.domain.userproblem.repository.UserProblemRepository;
import com.example.studiz.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 단순 조회는 readOnly를 권장합니다.
public class GetProblemService {

    private final ProblemRepository problemRepository;

    public Problem getProblemById(Long id) {
        return problemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("문제를 찾을수 없습니다"));
    }
}