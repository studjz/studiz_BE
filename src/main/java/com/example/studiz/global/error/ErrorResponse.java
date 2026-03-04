package com.example.studiz.global.error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String message;
    private int status;

    public static ErrorResponse of(String message,int status) {
        return ErrorResponse.builder()
                .message(message)
                .status(status)
                .build();
    }

}
