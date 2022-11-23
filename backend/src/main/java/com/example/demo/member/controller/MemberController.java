package com.example.demo.member.controller;

import com.example.demo.member.Member;
import com.example.demo.member.dto.EmailCheckRequest;
import com.example.demo.member.dto.MemberUpdateRequest;
import com.example.demo.member.request.MemberRegisterRequest;
import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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

    @GetMapping("/myInfo")
    public Member myInfo(@AuthenticationPrincipal Member member) {
        log.info("login member {}", member);

        Member find = memberService.findById(member);
        log.info("find member {}", find);
        return find;
    }

    @PostMapping("/update")
    public void update(@AuthenticationPrincipal Member member, @RequestBody MemberUpdateRequest request) {

        Member find = memberService.findById(member);
        if (Objects.equals(member.getId(), find.getId())) {
            log.info("update request =  {} ",request);
            memberService.update(Member.builder()
                    .id(find.getId())
                    .name(request.getName())
                    .email(request.getEmail())
                    .nickname(request.getNickname())
                    .build());
        }


    }

}
