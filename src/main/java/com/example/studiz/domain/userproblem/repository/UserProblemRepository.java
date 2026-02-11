package com.example.studiz.domain.userproblem.repository;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.user.User;
import com.example.studiz.domain.userproblem.UserProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProblemRepository extends JpaRepository<UserProblem,Long> {
    Optional<UserProblem> findByUserAndProblem(User user, Problem problem);
    long countByUser(User user); // 진행률 계산용
    long countByUserAndIsCorrectTrue(User user); // 정답률 계산용
}
