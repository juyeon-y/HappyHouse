package com.example.demo.member.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRegistRequest {
    private String password, email, name, nickname;

    public MemberRegistRequest(String password, String email, String name, String nickname) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
    }
}
