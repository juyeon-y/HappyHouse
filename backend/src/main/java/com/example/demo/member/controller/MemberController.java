package com.example.demo.member.controller;

import com.example.demo.member.request.MemberRegistRequest;
import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public void register(@Validated MemberRegistRequest request) {
        memberService.regist(request);
    }
}
