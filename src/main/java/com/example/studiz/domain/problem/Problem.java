package com.example.studiz.domain.problem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_problem")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long problemId;

    @Column(nullable = false)
    private String problemSubject;

    @Column(nullable = false)
    private String answerOne;

    @Column(nullable = false)
    private String answerTwo;

    @Column(nullable = false)
    private String answerThree;

    @Column(nullable = false)
    private String answerFour;
}
