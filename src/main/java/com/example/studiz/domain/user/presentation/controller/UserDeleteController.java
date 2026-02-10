package com.example.studiz.domain.user.presentation.controller;


import com.example.studiz.domain.user.repository.UserRepository;
import com.example.studiz.domain.user.service.UserDeleteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserDeleteController {

    private final UserDeleteService userDeleteService;

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userDeleteService.delete(id);
    }
}
