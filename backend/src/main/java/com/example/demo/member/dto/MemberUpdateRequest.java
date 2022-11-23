package com.example.demo.member.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class MemberUpdateRequest {
    private String name,nickname, email;
    private Integer id;
}
