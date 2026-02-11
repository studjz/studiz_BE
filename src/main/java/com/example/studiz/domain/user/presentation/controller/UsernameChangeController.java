package com.example.studiz.domain.user.presentation.controller;
import com.example.studiz.domain.user.service.UsernameChangeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UsernameChangeController {

    private final UsernameChangeService  usernameChangeService;







}
