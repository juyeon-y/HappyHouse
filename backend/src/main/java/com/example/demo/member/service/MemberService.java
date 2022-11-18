package com.example.demo.member.service;

import com.example.demo.member.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.request.MemberRegistRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public void regist(MemberRegistRequest request) {
        String encode = passwordEncoder.encode(request.getPassword());

        log.info("encode password : {} ", encode);
        Member member = Member.builder()
                .password(encode)
                .nickname(request.getNickname())
                .name(request.getName())
                .email(request.getEmail()).role("ROLE_USER").build();
        memberRepository.save(member);
    }
}
