package com.example.demo.user.service;

import com.example.demo.user.User;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.request.UserRegistRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @Test
    @DisplayName("회원 검색")
    void findByEmail() {
        String email = "suker800@gmail.com";
        User byEmail = userRepository.findByEmail(email);
        log.info("user {}", byEmail.getEmail());
    }

    @Test
    @DisplayName("회원 가입")
    void regist(){

        UserRegistRequest userRegistRequest = new UserRegistRequest("123", "a@a.com", "ㅌㅌ", "ㅂㅈㄷㄱ");
        userService.regist(userRegistRequest);

    }
}