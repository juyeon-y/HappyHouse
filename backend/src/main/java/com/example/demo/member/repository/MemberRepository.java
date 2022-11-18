package com.example.demo.member.repository;

import com.example.demo.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberRepository {

    Member findByEmail(String email);

    void save(Member member);
}
