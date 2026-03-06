package com.example.studiz.domain.main.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MakeLoadRequest {
    private String mapSubject;
    private String mapText;
    private String link;
    private String mapMajor;
}
