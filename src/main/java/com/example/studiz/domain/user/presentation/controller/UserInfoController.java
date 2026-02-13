package com.example.studiz.domain.user.presentation.controller;

import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.presentation.dto.response.UserInfoResponse;
import com.example.studiz.domain.user.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserInfoController {

    private UserInfoService userInfoService;

    @GetMapping("/info")
    public ResponseEntity<User> getUserInfo(@RequestHeader("Authorization") String token){
     User user = userInfoService.userInfo(token);
       return ResponseEntity.ok(user);
    }
}
