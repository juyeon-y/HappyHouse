package com.example.demo.user.controller;

import com.example.demo.user.request.UserRegistRequest;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void register(@Validated UserRegistRequest request) {
        userService.regist(request);
    }
}
