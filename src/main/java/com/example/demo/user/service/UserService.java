package com.example.demo.user.service;

import com.example.demo.user.User;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.request.UserRegistRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void regist(UserRegistRequest request) {
        String encode = passwordEncoder.encode(request.getPassword());

        log.info("encode password : {} ", encode);
        User user= User.builder()
                .password(encode)
                .nickname(request.getNickname())
                .name(request.getName())
                .email(request.getEmail()).role("ROLE_USER").build();
        userRepository.save(user);
    }
}
