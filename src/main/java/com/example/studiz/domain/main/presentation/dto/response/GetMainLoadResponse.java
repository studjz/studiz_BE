package com.example.studiz.domain.main.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetMainLoadResponse {
    private String mapSubject;
    private String mapText;
    private String link;
}
