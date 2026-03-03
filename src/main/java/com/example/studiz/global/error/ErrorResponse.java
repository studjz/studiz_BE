package com.example.studiz.global.exception.Response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String message;

    public static ErrorResponse of(String message) {
        return new ErrorResponse(message);
    }

}
