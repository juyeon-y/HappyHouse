package com.example.demo.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class Member implements UserDetails {
    private Integer id;
    private  String nickname;
    private  String email;
    private  String password;
    private  String name;
    private  String role;


    public Member(String nickname, String email, String password, String name, String role) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }
    @Builder
    public Member(String nickname, String email, String password, String name, String role, Integer id) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role);
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
