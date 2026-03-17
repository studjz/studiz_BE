package com.example.studiz.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MajorType {
    BACKEND("벡엔드"),
    FRONTEND("프론트엔드"),
    APP("앱");

    private final String krName;
}
