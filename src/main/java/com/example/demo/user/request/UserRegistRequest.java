package com.example.demo.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegistRequest {
    private String password, email, name, nickname;

    public UserRegistRequest(String password, String email, String name, String nickname) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
    }
}
