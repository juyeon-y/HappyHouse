package com.example.demo.member.service;

import com.example.demo.member.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.request.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public void regist(MemberRegisterRequest request) {
        String encode = passwordEncoder.encode(request.getPassword());

        log.info("encode password : {} ", encode);
        Member member = Member.builder()
                .password(encode)
                .nickname(request.getNickname())
                .name(request.getName())
                .email(request.getEmail()).role("ROLE_USER").build();
        memberRepository.save(member);
    }

    public boolean checkEmail(String email) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findByEmail(email));
        return member.isPresent();
    }

    public Member findById(Member member){

        return memberRepository.findById(member.getId());
    }

    public void update(Member member) {
        memberRepository.update(member);
    }


}
