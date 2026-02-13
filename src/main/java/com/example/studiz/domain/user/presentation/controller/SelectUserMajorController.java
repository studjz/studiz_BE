package com.example.studiz.domain.user.presentation.controller;

import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.presentation.dto.request.UserMajorRequest;
import com.example.studiz.domain.user.service.SelectUserMajorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/main")
public class SelectUserMajorController {
    private final SelectUserMajorService selectUserMajorService;


    @PostMapping("/major")
    public ResponseEntity<User> seleteUserMajor(@RequestBody UserMajorRequest userMajorRequest, @RequestHeader("Authorization") String token) {
        String major = userMajorRequest.getUserMajor();
        User user =  selectUserMajorService.seleteUserMajor(token,major);
        return ResponseEntity.ok(user);
    }

}
