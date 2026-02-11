package com.example.studiz.domain.user.presentation.controller;


import com.example.studiz.domain.user.service.UserDeleteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserDeleteController {

    private final UserDeleteService userDeleteService;

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String token) {
        userDeleteService.delete(token);
    }

}
