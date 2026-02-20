package com.example.studiz.domain.problem.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RetryAnswerRequest {
    private Long problemId;
    private String userAnswer;
}
