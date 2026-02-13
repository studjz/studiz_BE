package com.example.studiz.domain.user.presentation.controller;

import com.example.studiz.domain.user.User;
import com.example.studiz.domain.user.presentation.dto.request.NameChangeRequest;
import com.example.studiz.domain.user.service.UsernameChangeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UsernameChangeController {

    private final UsernameChangeService  usernameChangeService;

    @PatchMapping("/update")
    public ResponseEntity<User> changeUsername(@RequestHeader("Authorization") String token,@RequestBody NameChangeRequest nameChangeRequest) {
        String username = nameChangeRequest.getUsername();
        User user = usernameChangeService.changeUsername(token, username);
        return ResponseEntity.ok().body(user);
    }




}
