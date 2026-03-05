package com.example.studiz.global.error.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //user
    Duplication_School_Id(409, "이미 등록된 학번입니다."),
    User_Name_exceed(400, "아이디 길이가 초과었습니다"),


    //login
    Wrong_Id_Password(400, "아이디나 비밀번호가 틀렸습니다."),
    User_Not_Found(404, "유저를 찾지 못하였습니다."),

    //problem
    PROBLEM_NOT_FOUND(404,"문제를 찾지 못하였습니다."),
    No_Problem_Record(404,"해당 믄제에 대한 기록이 없습니다."),

    //token
    No_Access_Token(401,"인증 토큰이 없습니다."),

    //major
    Not_Selet_Major(400, "전공은 1개 선택해야 합니다."),

    //answer
    Blink_Answer(400, "공백을 제출할수 없습니다,");


    private int status;
    private String message;

}
