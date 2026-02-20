package com.example.studiz.domain.problem.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WrongProblemResponse {
    private Long id; // 또는 problemId (엔티티 필드명에 맞게)
    private String  problemSubject; // 문제 제목
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;// 문제 내용
}
