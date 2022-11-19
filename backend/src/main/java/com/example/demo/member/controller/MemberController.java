package com.example.demo.member.controller;

import com.example.demo.member.dto.EmailCheckRequest;
import com.example.demo.member.request.MemberRegisterRequest;
import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
@CrossOrigin(value = {"*"}, exposedHeaders = {"Authorization"})
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public void register(@Validated @RequestBody MemberRegisterRequest request) {
        log.info("회원 가입 {}", request);
        memberService.regist(request);
    }

    @PostMapping("/checkEmail")
    public boolean checkEmail(@RequestBody EmailCheckRequest email) {
        log.info("email {} ", email);
        return !memberService.checkEmail(email.getEmail());
    }
}
