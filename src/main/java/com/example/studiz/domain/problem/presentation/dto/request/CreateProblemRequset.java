package com.example.studiz.domain.problem.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateProblemRequset {
    private String problemSubject;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;;
}
