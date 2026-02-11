package com.example.studiz.domain.userproblem;


import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
@Table(name = "tbl_user_problem")
public class UserProblem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    private boolean isCorrect; // 해당 문제를 맞혔는지 여부

    @Builder
    public UserProblem(User user, Problem problem, boolean isCorrect) {
        this.user = user;
        this.problem = problem;
        this.isCorrect = isCorrect;
    }

    // 이미 푼 문제일 경우 결과만 업데이트하는 메서드
    public void updateResult(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}